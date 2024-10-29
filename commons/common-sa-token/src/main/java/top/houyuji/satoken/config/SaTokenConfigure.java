package top.houyuji.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiVersion = "/api/v1";
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> {
            SaRouter.match(apiVersion + "/**")
                    .notMatch(apiVersion + "/auth/login")
                    .check(r -> StpUtil.checkLogin());
            SaRouter.match(apiVersion + "/sys/**", r -> StpUtil.checkRole("admin"));
            // 用户管理
            SaRouter.match(SaHttpMethod.POST).match(apiVersion + "/sys/user", r -> StpUtil.checkPermission("sys:user:add"));
            SaRouter.match(SaHttpMethod.DELETE).match(apiVersion + "/sys/user", r -> StpUtil.checkPermission("sys:user:delete"));
            SaRouter.match(SaHttpMethod.PUT).match(apiVersion + "/sys/user", r -> StpUtil.checkPermission("sys:user:update"));
            SaRouter.match(SaHttpMethod.GET).match(apiVersion + "/sys/user", r -> StpUtil.checkPermission("sys:user:query"));
            SaRouter.match(SaHttpMethod.PUT).match(apiVersion + "/sys/user/resetPassword", r -> StpUtil.checkPermission("sys:user:update"));
            // 角色管理
            SaRouter.match(SaHttpMethod.POST).match(apiVersion + "/sys/role", r -> StpUtil.checkPermission("sys:role:add"));
            SaRouter.match(SaHttpMethod.DELETE).match(apiVersion + "/sys/role", r -> StpUtil.checkPermission("sys:role:delete"));
            SaRouter.match(SaHttpMethod.PUT).match(apiVersion + "/sys/role", r -> StpUtil.checkPermission("sys:role:update"));
            SaRouter.match(SaHttpMethod.GET).match(apiVersion + "/sys/role", r -> StpUtil.checkPermission("sys:role:query"));
            SaRouter.match(SaHttpMethod.PUT).match(apiVersion + "/sys/role/assignPermission", r -> StpUtil.checkPermission("sys:role:assignPermission"));
            // 权限管理
            SaRouter.match(SaHttpMethod.POST).match(apiVersion + "/sys/permission", r -> StpUtil.checkPermission("sys:permission:add"));
            SaRouter.match(SaHttpMethod.DELETE).match(apiVersion + "/sys/permission", r -> StpUtil.checkPermission("sys:permission:delete"));
            SaRouter.match(SaHttpMethod.PUT).match(apiVersion + "/sys/permission", r -> StpUtil.checkPermission("sys:permission:update"));
            SaRouter.match(SaHttpMethod.GET).match(apiVersion + "/sys/permission", r -> StpUtil.checkPermission("sys:permission:query"));
            SaRouter.match(SaHttpMethod.GET).match(apiVersion + "/sys/permission/menu/tree", r -> StpUtil.checkPermission("sys:permission:query"));
            // 系统公告管理
            SaRouter.match(SaHttpMethod.POST).match(apiVersion + "/sys/notice", r -> StpUtil.checkPermission("sys:notice:add"));
            SaRouter.match(SaHttpMethod.DELETE).match(apiVersion + "/sys/notice", r -> StpUtil.checkPermission("sys:notice:delete"));
            SaRouter.match(SaHttpMethod.PUT).match(apiVersion + "/sys/notice", r -> StpUtil.checkPermission("sys:notice:update"));
            SaRouter.match(SaHttpMethod.GET).match(apiVersion + "/sys/notice", r -> StpUtil.checkPermission("sys:notice:query"));
        }));
    }
}
