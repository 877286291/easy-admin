package top.houyuji.satoken.service;

import jakarta.annotation.Nullable;
import top.houyuji.satoken.domain.dto.UserInfoDTO;

import java.util.Optional;


public interface UserLoginService {

    /**
     * 根据用户名获取商户识别码
     *
     * @param username 用户名
     * @return 商户识别码
     */
    Optional<String> getSysCodeByUsername(String username);

    /**
     * 根据用户名获取用户信息,管理端登录
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfoDTO findByUsername(String username);

    /**
     * 根据用户名获取用户信息，商户端登录
     *
     * @param username 用户名
     * @param sysCode  商户识别码
     * @return 用户信息
     */
    UserInfoDTO findByUsername(String username, @Nullable String sysCode);
}