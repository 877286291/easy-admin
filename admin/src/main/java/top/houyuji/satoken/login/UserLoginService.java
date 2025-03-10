package top.houyuji.satoken.login;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.houyuji.basic.domain.entity.BasOrg;
import top.houyuji.basic.domain.entity.BasPermission;
import top.houyuji.basic.domain.entity.BasRole;
import top.houyuji.basic.domain.entity.BasUser;
import top.houyuji.basic.service.BasOrgService;
import top.houyuji.basic.service.BasPermissionService;
import top.houyuji.basic.service.BasRoleService;
import top.houyuji.basic.service.BasUserService;
import top.houyuji.common.base.exception.UsernameNotFoundException;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.common.satoken.domain.dto.UserInfoDTO;
import top.houyuji.common.satoken.domain.dto.UserOrgInfoDTO;
import top.houyuji.sys.domain.entity.SysPermission;
import top.houyuji.sys.domain.entity.SysRole;
import top.houyuji.sys.domain.entity.SysUser;
import top.houyuji.sys.service.SysPermissionService;
import top.houyuji.sys.service.SysRoleService;
import top.houyuji.sys.service.SysUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserLoginService implements top.houyuji.common.satoken.service.UserLoginService {
    private final SysUserService sysUserService;
    private final SysRoleService sysRoleService;
    private final SysPermissionService sysPermissionService;


    private final BasUserService basUserService;
    private final BasRoleService basRoleService;
    private final BasPermissionService basPermissionService;
    private final BasOrgService basOrgService;

    @Override
    public Optional<String> getSysCodeByUsername(String username) {
        return Optional.ofNullable(basUserService.getSysCodeByUsername(username));
    }

    @Override
    public UserInfoDTO findByUsername(String username) {
        return adminLogin(username);
    }

    @Override
    public UserInfoDTO findByUsername(String username, @Nullable String sysCode) {
        return tenantLogin(username);
    }

    /**
     * 商户端登录
     *
     * @param username .
     * @return .
     */
    private UserInfoDTO adminLogin(String username) {
        SysUser user = sysUserService.getByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new UsernameNotFoundException("用户已被禁用");
        }
        // 角色
        List<SysRole> roles = sysRoleService.listByUserId(user.getId());
        // 权限
        List<SysPermission> permissions = sysPermissionService.listByUserId(user.getId());

        UserInfoDTO userInfo = new UserInfoDTO();

        // id
        userInfo.setId(user.getId());
        // 用户名
        userInfo.setUsername(user.getUsername());
        // 密码
        userInfo.setPassword(user.getPassword());
        // 昵称
        userInfo.setNickname(user.getNickname());
        // 性别
//        userInfo.setGender(user.getGender());
        // 邮箱
        userInfo.setEmail(user.getEmail());
        // 电话
        userInfo.setPhone(user.getPhone());
        // 头像
        userInfo.setAvatar(user.getAvatar());
        // 角色
        userInfo.setRoles(getRolesByAdmin(roles));
        // 权限
        userInfo.setPermissions(getPermissionsByAdmin(permissions));
        // 是否启用
        userInfo.setEnabled(user.getEnabled());
        // 系统编码
        userInfo.setSysCode(null);
        return userInfo;
    }

    /**
     * 履约端登录
     *
     * @param username .
     * @return .
     */
    public UserInfoDTO tenantLogin(String username) {
        BasUser user = basUserService.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new UsernameNotFoundException("用户已被禁用");
        }
        BasOrg orgInfo = basOrgService.getById(user.getOrgId());
        //商户有效期
        basOrgService.checkOrgExpiredForLogin(orgInfo.getId());
        // 角色
        List<BasRole> roles = basRoleService.findByUserId(user.getId());
        // 权限
        List<BasPermission> permissions = basPermissionService.listByUserId(user.getId());
        UserInfoDTO userInfo = new UserInfoDTO();
        // id
        userInfo.setId(user.getId());
        // 用户名
        userInfo.setUsername(user.getUsername());
        // 密码
        userInfo.setPassword(user.getPassword());
        // 昵称
        userInfo.setNickname(user.getNickname());
        // 头像
        userInfo.setAvatar(user.getAvatar());
        // 邮箱
        userInfo.setEmail(user.getEmail());
        // 电话
        userInfo.setPhone(user.getPhone());
        // 系统编码
        userInfo.setSysCode(user.getSysCode());
        // 机构
        UserOrgInfoDTO UserOrgInfoDTO = new UserOrgInfoDTO();
        UserOrgInfoDTO.setId(orgInfo.getId());
        UserOrgInfoDTO.setName(orgInfo.getName());
        UserOrgInfoDTO.setPath(orgInfo.getPath());
        userInfo.setOrgInfo(UserOrgInfoDTO);
        // 角色
        userInfo.setRoles(getRoles(roles));
        // 权限
        userInfo.setPermissions(getPermissions(permissions));
        // 是否启用
        userInfo.setEnabled(user.getEnabled());
        return userInfo;
    }

    private List<String> getPermissionsByAdmin(List<SysPermission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(SysPermission::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRolesByAdmin(List<SysRole> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(SysRole::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getPermissions(List<BasPermission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(BasPermission::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRoles(List<BasRole> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(BasRole::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }
}
