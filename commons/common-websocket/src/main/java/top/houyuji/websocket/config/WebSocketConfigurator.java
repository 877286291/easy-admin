package top.houyuji.websocket.config;

import cn.dev33.satoken.error.SaErrorCode;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static cn.dev33.satoken.exception.NotLoginException.*;

@Component
public class WebSocketConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        Map<String, List<String>> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("token")) {
            String token = parameterMap.get("token").get(0);
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId == null) {
                throw NotLoginException.newInstance(StpUtil.TYPE, INVALID_TOKEN, INVALID_TOKEN_MESSAGE, token).setCode(SaErrorCode.CODE_11012);
            }
            super.modifyHandshake(sec, request, response);
        } else {
            throw NotLoginException.newInstance(StpUtil.TYPE, NOT_TOKEN, NOT_TOKEN_MESSAGE, null).setCode(SaErrorCode.CODE_11011);
        }
    }
}