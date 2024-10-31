package top.houyuji.satoken.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Service;
import top.houyuji.satoken.domain.dto.UserInfoDTO;
import top.houyuji.satoken.utils.SaTokenUtil;

import java.util.List;

@Service
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserInfoDTO currentUser = SaTokenUtil.getCurrentUser();
        return currentUser.getPermissions();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        UserInfoDTO currentUser = SaTokenUtil.getCurrentUser();
        return currentUser.getRoles();
    }
}
