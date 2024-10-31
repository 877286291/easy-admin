package top.houyuji.satoken.login;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.houyuji.basic.domain.entity.BasPermission;
import top.houyuji.basic.domain.entity.BasRole;
import top.houyuji.basic.service.BasPermissionService;
import top.houyuji.basic.service.BasRoleService;
import top.houyuji.common.base.core.UserInfo;
import top.houyuji.sys.domain.entity.SysPermission;
import top.houyuji.sys.domain.entity.SysRole;
import top.houyuji.sys.service.SysPermissionService;
import top.houyuji.sys.service.SysRoleService;

import java.util.List;

@Service
@AllArgsConstructor
public class StpInterfaceService implements StpInterface {
    private final SysPermissionService sysPermissionService;
    private final BasPermissionService basPermissionService;
    private final SysRoleService sysRoleService;
    private final BasRoleService basRoleService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSession();
        UserInfo userinfo = (UserInfo) session.get("userinfo");
        String userId = userinfo.getId();
        if (userinfo.getSysCode() == null) {
            List<SysPermission> permissions = sysPermissionService.listByUserId(userId);
            return permissions.stream().map(SysPermission::getPermission).toList();
        } else {
            List<BasPermission> permissions = basPermissionService.listByUserId(userId);
            return permissions.stream().map(BasPermission::getPermission).toList();
        }
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSession();
        UserInfo userinfo = (UserInfo) session.get("userinfo");
        String userId = userinfo.getId();
        if (userinfo.getSysCode() == null) {
            List<SysRole> roles = sysRoleService.listByUserId(userId);
            return roles.stream().map(SysRole::getCode).toList();
        } else {
            List<BasRole> roles = basRoleService.findByUserId(userId);
            return roles.stream().map(BasRole::getCode).toList();
        }
    }
}
