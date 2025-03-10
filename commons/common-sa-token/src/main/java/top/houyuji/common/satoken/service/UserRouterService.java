package top.houyuji.common.satoken.service;

import top.houyuji.common.satoken.domain.vo.RouteVO;

import java.util.List;


public interface UserRouterService {

    /**
     * 租户端用户路由
     *
     * @param userId 用户id
     * @return 路由
     */
    List<RouteVO> getRoutes(String userId);

    /**
     * 业务端用户路由
     *
     * @param userId  用户id
     * @param sysCode 系统标识
     * @return 路由
     */
    List<RouteVO> getRoutes(String userId, String sysCode);
}
