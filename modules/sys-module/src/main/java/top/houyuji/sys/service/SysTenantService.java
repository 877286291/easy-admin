package top.houyuji.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.houyuji.basic.domain.entity.BasRole;
import top.houyuji.basic.domain.entity.BasRolePermission;
import top.houyuji.basic.domain.entity.BasUser;
import top.houyuji.basic.domain.entity.BasUserRole;
import top.houyuji.basic.service.BasRoleService;
import top.houyuji.basic.service.BasUserService;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.exception.ServiceException;
import top.houyuji.common.base.utils.CollectionUtil;
import top.houyuji.common.base.utils.PasswordUtil;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.common.mybatis.core.service.BaseService;
import top.houyuji.common.query.mybatis.plus.QueryHelper;
import top.houyuji.sys.domain.dto.TenantBasicConfigDTO;
import top.houyuji.sys.domain.dto.TenantDTO;
import top.houyuji.sys.domain.dto.TenantSmallDTO;
import top.houyuji.sys.domain.entity.SysTenant;
import top.houyuji.sys.domain.entity.SysTenantPermission;
import top.houyuji.sys.domain.query.TenantQuery;
import top.houyuji.sys.mapper.SysTenantMapper;
import top.houyuji.sys.service.mapstruct.SysTenantBasicConfigMapstruct;
import top.houyuji.sys.service.mapstruct.SysTenantSmallMapstruct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SysTenantService extends BaseService<SysTenantMapper, SysTenant> {
    private final SysTenantSmallMapstruct mapstruct;
    private final SysTenantBasicConfigMapstruct mapstructBasicConfig;
    @Lazy
    @Resource
    private BasUserService basUserService;
    @Lazy
    @Resource
    private BasRoleService basRoleService;
    @Lazy
    @Resource
    private SysTenantPermissionService sysTenantPermissionService;

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   需要排除的id
     * @return 是否存在
     */
    public Boolean existsCode(String code, String id) {
        if (StrUtil.isBlank(id)) {
            return baseMapper.existsCode(code);
        }
        return baseMapper.existsCodeByIdNot(code, id);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<TenantDTO> page(TenantQuery query) {
        IPage<TenantDTO> page = QueryHelper.toPage(query);
        List<TenantDTO> list = baseMapper.list(page, query);
        return new JsfPage<>(page.getCurrent(), page.getSize(), page.getTotal(), list);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    public List<TenantDTO> list(TenantQuery query) {
        return baseMapper.list(null, query);
    }

    /**
     * 保存
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(TenantSmallDTO dto) {
        BasUser user = basUserService.findByUsername(dto.getLinkTel());
        if (null != user && StrUtil.isNotBlank(user.getUsername())) {
            throw new ServiceException("手机号已被使用");
        }
        SysTenant organization = convert(dto);
        save(organization);
        // 初始化用户角色
        initUserRoleProduct(organization);
    }

    /**
     * 更新
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(TenantSmallDTO dto) {
        SysTenant tenant = baseMapper.selectById(dto.getId());
        if (null == tenant) {
            throw new ServiceException("商户不存在");
        }
        boolean isEditProduct = false;
        boolean isEditLinkTel = false;
        if (!tenant.getLinkTel().equals(dto.getLinkTel())) {
            BasUser user = basUserService.findByUsername(dto.getLinkTel());
            if (null != user && StrUtil.isNotBlank(user.getUsername())) {
                throw new ServiceException("手机号已被使用");
            }
            isEditLinkTel = true;
        }
        String productId = tenant.getProductId();
        if (StrUtil.isNotBlank(productId) &&
                !productId.equals(dto.getProductId())) {
            isEditProduct = true;
        }


        SysTenant organization = convert(dto);
        if (isEditProduct) {
            isUpdateProduct(organization, tenant);
        }
        if (isEditLinkTel) {
            isUpdateUser(organization, tenant);
        }

        BeanUtil.copyProperties(organization, dto, CopyOptions.create().ignoreNullValue());
        updateById(organization);
    }


    /**
     * 重置密码
     *
     * @param id .
     */
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(String id) {
        SysTenant tenant = baseMapper.selectById(id);
        if (null == tenant) {
            throw new ServiceException("商户不存在");
        }
        isUpdateUser(tenant, tenant);
    }

    /**
     * 获取商户基本配置
     *
     * @param id 商户id
     * @return 商户基本配置
     */
    public TenantBasicConfigDTO getTenantBasicConfig(String id) {
        SysTenant tenant = baseMapper.selectById(id);
        if (null == tenant) {
            throw new ServiceException("商户不存在");
        }
        return mapstructBasicConfig.toDTO(tenant);
    }

    /**
     * 更新商户基本配置
     *
     * @param dto 商户基本配置
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateTenantOrgBasicConfig(TenantBasicConfigDTO dto) {
        SysTenant tenant = baseMapper.selectById(dto.getId());
        if (null == tenant) {
            throw new ServiceException("商户不存在");
        }
        tenant.setUsedEndTime(dto.getUsedEndTime());
        updateById(tenant);
    }


    private SysTenant convert(TenantSmallDTO dto) {
        SysTenant organization = new SysTenant();
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            id = IdUtil.getSnowflakeNextIdStr();
        }
        // 设置id
        organization.setId(id);
        // 设置商户识别码
        organization.setSysCode(dto.getSysCode());
        // 商户名称
        organization.setName(dto.getName());
        organization.setParentId(null);
        // 联系人
        organization.setLinkMan(dto.getLinkMan());
        // 联系电话
        organization.setLinkTel(dto.getLinkTel());
        // 联系邮箱
        organization.setLinkEmail(dto.getLinkEmail());
        // 机构地址
        organization.setAddress(dto.getAddress());
        // 总部标识
        organization.setSystem(true);
        // 启用saas
        organization.setSaas(true);
        // 产品ID
        organization.setProductId(dto.getProductId());
        // 截止日期
        organization.setUsedEndTime(dto.getUsedEndTime());
        //网点等级
        organization.setLevel(1);
        // 机构路径
        organization.setPath(id);
        // 机构类型
        organization.setType(1);
        // 备注
        organization.setMemo(dto.getMemo());
        //是否启用
        Boolean enabled = dto.getEnabled();
        if (null == enabled) {
            enabled = true;
        }
        organization.setEnabled(enabled);
        // 创建人
        organization.setCreatedBy(dto.getCreatedBy());
        // 创建时间
        organization.setCreated(dto.getCreated());
        // 更新人
        organization.setModifiedBy(dto.getModifiedBy());
        // 更新时间
        organization.setModified(dto.getModified());
        return organization;
    }

    private void initUserRoleProduct(SysTenant organization) {
        // 1、为厂商自动创建管理角色，角色对应权限默认系统所有
        BasRole role = new BasRole();
        String roleId = IdUtil.getSnowflakeNextIdStr();
        role.setId(roleId);
        role.setCreated(new Date());
        role.setCreatedBy(organization.getCreatedBy());
        role.setCode(organization.getSysCode());
        role.setSysCode(organization.getSysCode());
        role.setName("管理角色");
        role.setSystem(true);
        role.setEnabled(true);
        role.setOrgId(organization.getId());
        role.setDescription("系统自动创建-SYSTEM");
        // 1.1 角色-权限绑定
        List<SysTenantPermission> permissions = sysTenantPermissionService.findByProductId(organization.getProductId());
        List<BasRolePermission> rolePermissions = new ArrayList<>(permissions.size());
        if (CollectionUtil.isNotEmpty(permissions)) {
            rolePermissions = permissions.stream().map(e -> new BasRolePermission(roleId, e.getId())).toList();
        }
        // 2、厂商自动开户，同时为账户绑定初始角色
        BasUser user = new BasUser();
        String userId = IdUtil.getSnowflakeNextIdStr();
        user.setId(userId);
        user.setCreated(new Date());
        user.setCreatedBy(organization.getCreatedBy());
        user.setUsername(organization.getLinkTel());
        String pwd = RandomUtil.randomString(9);
        log.info("creat: {}", pwd);
        user.setPassword(PasswordUtil.encoder(pwd));
        user.setNickname("管理员");
        user.setPhone(organization.getLinkTel());
        user.setSystem(true);
        user.setEnabled(true);
        user.setOrgId(organization.getId());
        user.setSysCode(organization.getSysCode());

        // 3、用户-角色绑定
        BasUserRole userRole = new BasUserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        // 4、保存
        basRoleService.save(role);
        basRoleService.saveRolePermission(rolePermissions);
        basUserService.save(user);
        basUserService.saveUserRole(List.of(userRole));

    }

    /**
     * 更新了产品
     *
     * @param newOrg .
     * @param oldOrg .
     */
    private void isUpdateProduct(SysTenant newOrg, SysTenant oldOrg) {
        String productId = newOrg.getProductId();
        // 查询新产品权限
        List<SysTenantPermission> newProductPermission = sysTenantPermissionService.findByProductId(productId);

        sysTenantPermissionService.checkPermission(newProductPermission, List.of(oldOrg.getId()), List.of(oldOrg.getSysCode()));
    }

    /**
     * 更新了预留电话
     *
     * @param newOrg .
     * @param oldOrg .
     */
    private void isUpdateUser(SysTenant newOrg, SysTenant oldOrg) {
        BasUser user = basUserService.findByUsername(oldOrg.getLinkTel());
        if (null == user) {
            throw new ServiceException("机构管理员角色信息异常~~");
        }
        user.setModified(newOrg.getModified());
        user.setModifiedBy(newOrg.getModifiedBy());
        user.setUsername(newOrg.getLinkTel());
        user.setPhone(newOrg.getLinkTel());
        String pwd = RandomUtil.randomString(9);
        log.info("creat: {}", pwd);
        user.setPassword(PasswordUtil.encoder(pwd));
        // 更新用户信息
        basUserService.updateById(user);
    }
}
