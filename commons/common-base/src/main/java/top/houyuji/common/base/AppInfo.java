package top.houyuji.common.base;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AppInfo {
    /**
     * 应用名称
     */
    private String name;
    /**
     * 应用版本
     */
    private String version;
    /**
     * 应用端口
     */
    private String port;
    /**
     * 应用上下文
     */
    private String contextPath;
    /**
     * 应用PID
     */
    private String pid;
    /**
     * 应用启动时间
     */
    private String startTime;
    /**
     * 主机
     */
    private String host;
    /**
     * 环境
     */
    private String profile;
    /**
     * 是否为调试模式
     */
    private boolean debug;


}