package top.houyuji.basic.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.houyuji.basic.domain.dto.BasUserDTO;
import top.houyuji.basic.domain.dto.BasUserRestPasswordDTO;
import top.houyuji.basic.domain.dto.BasUserSaveDTO;
import top.houyuji.basic.domain.entity.BasUser;
import top.houyuji.basic.domain.entity.BasUserRole;
import top.houyuji.basic.domain.query.BasUserQuery;
import top.houyuji.basic.mapper.BasUserMapper;
import top.houyuji.basic.service.mapstruct.BasUserMapstruct;
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
public class BasUserService extends BaseService<BasUserMapper, BasUser> {
    private final BasUserMapstruct mapstruct;
    @Lazy
    @Resource
    private BasOrgService orgService;

    /**
     * 根据用户名获取租户编码
     *
     * @param username 用户名
     * @return 租户编码
     */
    public String getSysCodeByUsername(String username) {
        return baseMapper.getSysCodeByUsername(username);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    public BasUser findByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

    /**
     * 根据ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    public BasUser getById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 修改最后登录时间
     *
     * @param username 用户名
     * @param sysCode  租户编码
     */
    public void changeLastLoginTimeByUsername(String username, String sysCode) {
        baseMapper.changeLastLoginTimeByUsername(username, sysCode);
    }

    /**
     * 根据组织ID查询用户ID
     *
     * @param orgIds 组织ID
     * @return 用户ID
     */
    public Set<String> findUserIdsByOrgIds(List<String> orgIds) {
        return baseMapper.findUserIdByOrgIds(orgIds);
    }

    /**
     * 根据用户ID查询角色ID
     *
     * @param userIds 用户ID
     * @return 角色ID
     */
    public Set<String> findRoleIdsByUserIds(Collection<String> userIds) {
        return baseMapper.findRoleIdsByUserIds(userIds);
    }

    /**
     * 账号是否存在
     *
     * @param username 用户名
     * @param sysCode  租户编码
     * @param id       需要排除的用户ID
     * @return 是否存在
     */
    public Boolean existsUsername(String username, String sysCode, String id) {
        String _username = username;
        if (!username.contains("@")) {
            _username = username + "@" + sysCode;
        }
        if (StrUtil.isBlank(id)) {
            return baseMapper.existsUsername(_username);
        }
        return baseMapper.existsUsernameByIdNot(_username, id);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<BasUserDTO> page(BasUserQuery query) {
        IPage<BasUserDTO> page = QueryHelper.toPage(query);
        List<BasUserDTO> list = baseMapper.list(page, query);
        return QueryHelper.toJsfPage(page, list);
    }

    /**
     * 获取用户角色
     *
     * @param id 用户ID
     * @return 角色ID
     */
    public List<String> getRoleIds(String id) {
        return baseMapper.getRoleIds(id);
    }

    /**
     * 查询用户列表
     *
     * @param query 查询条件
     * @return 用户列表
     */
    public List<BasUserDTO> list(BasUserQuery query) {
        return baseMapper.list(null, query);
    }

    /**
     * 保存用户
     *
     * @param dto 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(BasUserSaveDTO dto) {
        String username = dto.getUsername();
        if (existsUsername(username, dto.getSysCode(), dto.getId())) {
            throw new ServiceException("用户名已存在");
        }
        if (StrUtil.isBlank(dto.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        String orgId = dto.getOrgId();
        if (StrUtil.isBlank(orgId)) {
            throw new ServiceException("机构不能为空");
        }
        boolean accountNum = orgService.checkAccountNum(orgId);
        if (accountNum) {
            throw new ServiceException("账号数量已达上限");
        }
        dto.setUsername(username + "@" + dto.getSysCode());
        BasUser user = mapstruct.toEntity(dto);
        user.setSystem(false);
        save(user);
        // 保存用户角色
        List<String> roleIds = dto.getRoleIds();
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<BasUserRole> userRoles = CollectionUtil.listToList(roleIds, roleId -> new BasUserRole(user.getId(), roleId));
            saveUserRole(userRoles);
        }
    }

    /**
     * 更新用户
     *
     * @param dto 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(BasUserSaveDTO dto) {
        String username = dto.getUsername();
        if (existsUsername(username, dto.getSysCode(), dto.getId())) {
            throw new ServiceException("用户名已存在");
        }
        String orgId = dto.getOrgId();
        if (StrUtil.isBlank(orgId)) {
            throw new ServiceException("机构不能为空");
        }
        BasUser entity = getById(dto.getId());
        if (entity == null) {
            throw new ServiceException("用户不存在");
        }
        dto.setPassword(null);
        dto.setUsername(null);
        BasUser user = mapstruct.toEntity(dto);
        user.setSystem(false);
        BeanUtil.copyProperties(user, entity, CopyOptions.create().setIgnoreNullValue(true));
        updateById(entity);
        // 删除用户角色
        baseMapper.deleteUserRoleByUserId(entity.getId());
        // 保存用户角色
        List<String> roleIds = dto.getRoleIds();
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<BasUserRole> userRoles = CollectionUtil.listToList(roleIds, roleId -> new BasUserRole(user.getId(), roleId));
            saveUserRole(userRoles);
        }
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        BasUser user = getById(id);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        if (Boolean.TRUE.equals(user.getSystem())) {
            throw new ServiceException("系统用户不允许删除");
        }
        removeById(id);
        baseMapper.deleteUserRoleByUserId(id);
    }

    /**
     * 重置密码
     *
     * @param dto 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void restPassword(BasUserRestPasswordDTO dto) {
        BasUser user = getById(dto.getId());
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        restPassword(dto.getId(), dto.getPassword());
    }

    /**
     * 重置密码
     *
     * @param id 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void restPassword(String id, String password) {
        BasUser user = getById(id);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        user.setPassword(password);
        updateById(user);
    }


    /**
     * 保存用户角色
     *
     * @param userRoles 用户角色
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRole(List<BasUserRole> userRoles) {
        if (CollectionUtil.isEmpty(userRoles)) {
            return;
        }
        baseMapper.saveUserRole(userRoles);
    }

}
