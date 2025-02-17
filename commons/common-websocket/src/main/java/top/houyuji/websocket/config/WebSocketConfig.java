package top.houyuji.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import top.houyuji.websocket.handler.MyWebSocketHandler;
import top.houyuji.websocket.handler.VideoHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 注册的端点
        registry.addHandler(new MyWebSocketHandler(), "/ws")
                .addHandler(new VideoHandler(), "/video")
                .setAllowedOrigins("*");
    }
}
