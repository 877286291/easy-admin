package top.houyuji.common.satoken.utils;

import cn.dev33.satoken.stp.StpUtil;
import top.houyuji.common.base.AppUtil;
import top.houyuji.common.base.core.UserInfo;
import top.houyuji.common.satoken.domain.dto.UserInfoDTO;
import top.houyuji.common.satoken.service.UserLoginService;

import java.util.Optional;

public class SaTokenUtil {
    /**
     * 获取当前用户
     *
     * @return .
     */
    public static UserInfoDTO getCurrentUser() {
        UserLoginService userLoginService = AppUtil.getBean(UserLoginService.class);
        UserInfo userinfo = (UserInfo) StpUtil.getSession().get("userinfo");
        UserInfoDTO currentUser;
        String username = userinfo.getUsername();
        if (userinfo.getSysCode() != null) {
            Optional<String> sysCode = userLoginService.getSysCodeByUsername(username);
            currentUser = userLoginService.findByUsername(username, sysCode.orElse(null));
        } else {
            currentUser = userLoginService.findByUsername(username);
        }
        return currentUser;
    }

    /**
     * 获取当前用户id
     *
     * @return .
     */
    public static String getUserId() {
        UserInfoDTO currentUser = getCurrentUser();
        if (currentUser != null) {
            return currentUser.getId();
        }
        return null;
    }

    /**
     * 获取当前用户的系统编码
     *
     * @return .
     */
    public static String getSysCode() {
        UserInfoDTO currentUser = getCurrentUser();
        if (currentUser != null) {
            return currentUser.getSysCode();
        }
        return null;
    }

    /**
     * 获取当前username
     *
     * @return .
     */
    public static String getUsername() {
        UserInfoDTO currentUser = getCurrentUser();
        if (null != currentUser) {
            return currentUser.getUsername();
        }
        return null;
    }
}
