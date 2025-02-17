package top.houyuji.websocket.handler;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class VideoHandler extends BinaryWebSocketHandler {
    private static final String STREAM_URL = "https://eszhgd.qdgxzy.com:30200/sms/80010000002020000002/flv/hls/42000005321320000092_42000005321320000092.flv";
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final AtomicReference<Future<?>> streamingTask = new AtomicReference<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        // 开始流式传输数据到客户端
        streamingTask.set(executorService.submit(() -> streamVideo(session)));
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        // 取消流数据传输任务
        Future<?> task = streamingTask.getAndSet(null);
        if (task != null) {
            task.cancel(true);
        }
        log.info("WebSocket session closed: {}", session.getId());
    }

    private void streamVideo(WebSocketSession session) {
        try {
            URL url = new URL(STREAM_URL);
            URLConnection conn = url.openConnection();
            try (InputStream is = conn.getInputStream()) {
                byte[] buffer = new byte[8192]; // 增加缓冲区大小
                int bytesRead;
                while (!Thread.currentThread().isInterrupted() && session.isOpen() && (bytesRead = is.read(buffer)) != -1) {
                    // 发送到WebSocket客户端
                    session.sendMessage(new BinaryMessage(buffer, 0, bytesRead, true));

                    log.debug("Sent and saved {} bytes of video data", bytesRead);
                }
            }
        } catch (IOException e) {
            log.error("Error streaming", e);
        } finally {
            try {
                if (session.isOpen()) {
                    session.close();
                }
            } catch (IOException e) {
                log.error("Error closing WebSocket session", e);
            }
        }
    }
}