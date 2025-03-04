package top.houyuji.basic.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.houyuji.basic.domain.dto.BasRoleDTO;
import top.houyuji.basic.domain.entity.BasRole;
import top.houyuji.basic.domain.entity.BasRolePermission;
import top.houyuji.basic.domain.query.BasRoleQuery;
import top.houyuji.basic.mapper.BasRoleMapper;
import top.houyuji.basic.service.mapstruct.BasRoleMapstruct;
import top.houyuji.common.api.JsfPage;
import top.houyuji.common.base.exception.ServiceException;
import top.houyuji.common.base.utils.CollectionUtil;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.common.mybatis.core.service.BaseService;
import top.houyuji.common.query.mybatis.plus.QueryHelper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasRoleService extends BaseService<BasRoleMapper, BasRole> {
    private final BasRoleMapstruct basRoleMapstruct;

    /**
     * 根据用户ID获取角色
     *
     * @param userId 用户ID
     * @return 角色
     */
    public List<BasRole> findByUserId(String userId) {
        return baseMapper.findByUserId(userId);
    }

    /**
     * 根据组织ID获取管理员角色
     *
     * @param orgIds 组织ID
     * @return 角色
     */
    public Set<String> findAdminRoleIdsByOrgIds(List<String> orgIds) {
        return baseMapper.findAdminRoleIdsByOrgIds(orgIds);
    }

    /**
     * 根据商户码获取角色ID
     *
     * @param sysCode .
     * @return .
     */
    public Set<String> findRoleIdsBySysCodes(List<String> sysCode) {
        return baseMapper.findRoleIdsBySysCodes(sysCode);
    }

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    public List<BasRolePermission> findRolePermissionsByRoleIds(Collection<String> roleIds) {
        return baseMapper.findRolePermissionsByRoleIds(roleIds);
    }

    /**
     * code是否存在
     *
     * @param code code
     * @param id   需要排除的id
     * @return 是否存在
     */
    public Boolean existsCode(String code, String sysCode, String id) {
        if (StrUtil.isBlank(id)) {
            return baseMapper.existsCode(code, sysCode);
        }
        return baseMapper.existsCodeByIdNot(code, sysCode, id);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页
     */
    public JsfPage<BasRoleDTO> page(BasRoleQuery query) {
        QueryWrapper<BasRole> queryWrapper = QueryHelper.ofBean(query);
        IPage<BasRole> page = QueryHelper.toPage(query);
        page = baseMapper.selectPage(page, queryWrapper);

        List<BasRoleDTO> res = basRoleMapstruct.toDTOList(page.getRecords());
        return QueryHelper.toJsfPage(page, res);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 角色
     */
    public List<BasRoleDTO> list(BasRoleQuery query) {
        QueryWrapper<BasRole> queryWrapper = QueryHelper.ofBean(query);
        List<BasRole> res = baseMapper.selectList(queryWrapper);
        return basRoleMapstruct.toDTOList(res);
    }

    /**
     * 根据角色ID获取权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID
     */
    public List<String> getPermissionIdsByRoleId(String roleId) {
        return baseMapper.getPermissionIdsByRoleId(roleId);
    }

    /**
     * 保存
     *
     * @param dto 角色
     */
    public void save(BasRoleDTO dto) {
        BasRole entity = basRoleMapstruct.toEntity(dto);
        Boolean existsCode = existsCode(entity.getCode(), dto.getSysCode(), entity.getId());
        if (existsCode) {
            throw new ServiceException("code已存在");
        }
        save(entity);
    }

    /**
     * 更新
     *
     * @param dto 角色
     */
    public void updateById(BasRoleDTO dto) {
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            throw new ServiceException("id不能为空");
        }
        BasRole entity = getById(id);
        if (entity == null) {
            throw new ServiceException("数据不存在");
        }
        if (Boolean.TRUE.equals(entity.getSystem())) {
            throw new ServiceException("系统角色不允许修改");
        }
        BasRole update = basRoleMapstruct.toEntity(dto);
        BeanUtil.copyProperties(update, entity, CopyOptions.create().ignoreNullValue());
        updateById(entity);
    }

    /**
     * 删除
     *
     * @param id 角色id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        BasRole entity = getById(id);
        if (entity == null) {
            throw new ServiceException("数据不存在");
        }
        if (Boolean.TRUE.equals(entity.getSystem())) {
            throw new ServiceException("系统角色不允许删除");
        }
        // 是否有用户使用
        if (baseMapper.existsUserByRoleId(id)) {
            throw new ServiceException("有用户使用该角色，不允许删除");
        }

        removeById(id);
        // 删除角色权限
        baseMapper.deleteRolePermissionByRoleId(id);
    }

    /**
     * 重新授权
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     */
    @Transactional(rollbackFor = Exception.class)
    public void grantPermission(String roleId, List<String> permissionIds) {
        baseMapper.deleteRolePermissionByRoleId(roleId);
        if (CollectionUtil.isNotEmpty(permissionIds)) {
            List<BasRolePermission> rolePermissions = permissionIds.stream().map(permissionId -> new BasRolePermission(roleId, permissionId)).toList();
            saveRolePermission(rolePermissions);
        }
    }


    /**
     * 保存角色权限
     *
     * @param rolePermissions 角色权限
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveRolePermission(List<BasRolePermission> rolePermissions) {
        if (CollectionUtil.isEmpty(rolePermissions)) {
            return;
        }
        baseMapper.saveRolePermission(rolePermissions);
    }
}
