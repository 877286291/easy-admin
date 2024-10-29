package top.houyuji.satoken.utils;

import cn.dev33.satoken.stp.StpUtil;
import top.houyuji.common.base.AppUtil;
import top.houyuji.common.cache.core.EasyAdminCache;
import top.houyuji.satoken.domain.dto.UserInfoDTO;

public class SaTokenUtil {
    /**
     * 获取当前用户
     *
     * @return .
     */
    public static UserInfoDTO getCurrentUser() {
        // 获取 Sa-Token 登入的用户 ID
        String loginId = StpUtil.getLoginIdAsString();
        // 获取登入的用户信息
        EasyAdminCache easyAdminCache = AppUtil.getBean(EasyAdminCache.class);
        return (UserInfoDTO) easyAdminCache.getObject("user:" + loginId);
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
