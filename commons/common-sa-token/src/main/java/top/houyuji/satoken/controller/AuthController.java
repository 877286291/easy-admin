package top.houyuji.satoken.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.houyuji.common.base.R;
import top.houyuji.common.base.core.UserInfo;
import top.houyuji.common.base.utils.PasswordUtil;
import top.houyuji.common.base.utils.StrUtil;
import top.houyuji.common.cache.core.EasyAdminCache;
import top.houyuji.satoken.controller.mapstruct.LoginInfoMapstruct;
import top.houyuji.satoken.domain.LoginRequest;
import top.houyuji.satoken.domain.dto.UserInfoDTO;
import top.houyuji.satoken.domain.vo.LoginInfoVo;
import top.houyuji.satoken.domain.vo.RouteVO;
import top.houyuji.satoken.service.UserLoginService;
import top.houyuji.satoken.service.UserRouterService;
import top.houyuji.satoken.utils.SaTokenUtil;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Slf4j
@Tag(name = "系统：授权接口")
@RequiredArgsConstructor
public class AuthController {
    private final LoginInfoMapstruct loginInfoMapper;
    private final UserLoginService userLoginService;
    private final EasyAdminCache easyAdminCache;
    private final UserRouterService userRouterService;


    /**
     * 登录
     *
     * @param query 登录信息
     * @return 登录信息
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "登录成功"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "登录异常",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = R.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "认证失败",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = R.class))
            )
    })
    public R<LoginInfoVo> loginByUsername(@Validated @RequestBody LoginRequest query) {
        // 公钥加密私钥解密
        String password = query.getPassword();
        Boolean isTenantLogin = query.getTenantLogin();
        String username = query.getUsername();
        UserInfoDTO user;
        if (isTenantLogin) {
            Optional<String> sysCode = userLoginService.getSysCodeByUsername(username);
            user = userLoginService.findByUsername(username, sysCode.orElse(null));
        } else {
            user = userLoginService.findByUsername(username);
        }
        // 验证密码
        if (!PasswordUtil.matches(password, user.getPassword())) {
            return R.NG("用户名或密码错误");
        }
        // 登录成功
        StpUtil.login(user.getId());
        SaSession saSession = StpUtil.getSession();
        saSession.set("userinfo", new UserInfo(user.getId(), user.getUsername(), user.getSysCode()));
        // redis保存用户信息
//        easyAdminCache.setObject("user:" + user.getId(), user);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String token = tokenInfo.getTokenValue();
        // 返回用户信息
        LoginInfoVo res = loginInfoMapper.toDTO(user);
        res.setToken(token);
        return R.OK(res);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    @Operation(summary = "获取用户信息")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "获取用户信息成功"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "获取用户信息异常",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = R.class))
            )
    })
    public R<LoginInfoVo> userInfo() {
        UserInfoDTO currentUser = SaTokenUtil.getCurrentUser();
        return R.OK(loginInfoMapper.toDTO(currentUser));
    }

    /**
     * 获取用户路由
     */
    @GetMapping("/routes")
    @Operation(summary = "获取用户路由")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "获取用户路由成功"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "获取用户路由异常",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = R.class))
            )
    })
    public R<List<RouteVO>> routes() {
        UserInfoDTO currentUser = SaTokenUtil.getCurrentUser();
        if (currentUser == null) {
            return R.NG("获取当前用户失败");
        }
        if (StrUtil.isNotBlank(currentUser.getSysCode())) {
            return R.OK(userRouterService.getRoutes(currentUser.getId(), currentUser.getSysCode()));
        }
        return R.OK(userRouterService.getRoutes(currentUser.getId()));
    }

    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public R<String> logout() {
        String loginId = StpUtil.getLoginIdAsString();
        easyAdminCache.delete("user:" + loginId);
        StpUtil.logout();
        return R.OK("退出成功");
    }
}
