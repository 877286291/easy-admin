package top.houyuji.utils;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.xxl.job.core.biz.model.ReturnT;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class XxlJobUtil {
    private static final String API_LOGIN = "/login";
    private static final String API_ADD_JOB = "/jobinfo/add";
    private static final String API_UPDATE_JOB = "/jobinfo/update";
    private static final String API_REMOVE_JOB = "/jobinfo/remove";
    private static final String API_LIST_JOBS = "/jobinfo/pageList";

    private static String authCookie;
    private static String adminAddress;

    @Value("${xxl.job.admin.addresses}")
    private String configuredAdminAddress;

    /**
     * 登录XXL-JOB并获取cookie
     *
     * @return 登录是否成功
     */
    public static boolean login() {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", "admin");
        params.put("password", "123456");

        try (HttpResponse response = HttpRequest.post(ApiEndpoint.LOGIN.getFullUrl())
                .form(params)
                .execute()) {

            if (response.isOk()) {
                ReturnT<String> result = JSONUtil.toBean(response.body(), new TypeReference<>() {
                }, false);
                if (result.getCode() == ReturnT.SUCCESS_CODE) {
                    authCookie = response.header("Set-Cookie");
                    log.info("Login successful, cookie obtained: {}", authCookie);
                    return true;
                } else {
                    log.error("Login failed: {}", result.getMsg());
                }
            } else {
                log.error("Login request failed. Status code: {}", response.getStatus());
            }
        } catch (Exception e) {
            log.error("Error occurred while logging in to XXL-Job", e);
        }
        return false;
    }

    /**
     * 添加定时任务
     *
     * @param jobDesc         任务描述
     * @param executorHandler 执行器
     * @param executorParam   执行器参数
     * @param cronExpression  cron表达式
     * @return 操作结果
     */
    public static String addJob(String jobDesc, String executorHandler, String executorParam, String cronExpression) {
        Map<String, Object> params = createCommonJobParams(jobDesc, executorHandler, executorParam, cronExpression);
        return (String) doRequest(ApiEndpoint.ADD_JOB.getFullUrl(), params).getOrDefault("content", "");
    }

    /**
     * 修改定时任务
     *
     * @param jobId           任务ID
     * @param jobDesc         任务描述
     * @param executorHandler 执行器
     * @param executorParam   执行器参数
     * @param cronExpression  cron表达式
     * @return 操作结果
     */
    public static String updateJob(int jobId, String jobDesc, String executorHandler, String executorParam, String cronExpression) {
        Map<String, Object> params = createCommonJobParams(jobDesc, executorHandler, executorParam, cronExpression);
        params.put("id", jobId);
        return (String) doRequest(ApiEndpoint.UPDATE_JOB.getFullUrl(), params).getOrDefault("content", "");
    }

    /**
     * 创建通用的任务参数
     */
    private static Map<String, Object> createCommonJobParams(String jobDesc, String executorHandler, String executorParam, String cronExpression) {
        Map<String, Object> params = new HashMap<>();
        params.put("jobGroup", 1);  // 假设默认使用 jobGroup 1，如果需要可以作为参数传入
        params.put("jobDesc", jobDesc);
        params.put("author", "XXL");
        params.put("alarmEmail", "");
        params.put("scheduleType", "CRON");
        params.put("scheduleConf", cronExpression);
        params.put("cronGen_display", cronExpression);
        params.put("glueType", "BEAN");
        params.put("executorHandler", executorHandler);
        params.put("executorParam", executorParam);
        params.put("executorRouteStrategy", "FIRST");
        params.put("childJobId", "");
        params.put("misfireStrategy", "DO_NOTHING");
        params.put("executorBlockStrategy", "SERIAL_EXECUTION");
        params.put("executorTimeout", 0);
        params.put("executorFailRetryCount", 0);
        params.put("glueRemark", "GLUE代码初始化");
        return params;
    }

    /**
     * 删除定时任务
     *
     * @param jobId 任务ID
     * @return 操作结果
     */
    public static String removeJob(int jobId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", jobId);
        Map<String, Object> responseResult = doRequest(ApiEndpoint.REMOVE_JOB.getFullUrl(), params);
        return (String) responseResult.getOrDefault("content", "");
    }

    /**
     * 获取全部定时任务
     *
     * @return 任务列表
     */
    public static List<Map<String, Object>> listAllJobs() {
        Map<String, Object> params = new HashMap<>();
        params.put("jobGroup", 1);  // 假设默认使用 jobGroup 1，如果需要可以作为参数传入
        params.put("triggerStatus", -1);
        params.put("jobDesc", "");
        params.put("executorHandler", "");
        params.put("author", "");
        Map<String, Object> responseResult = doRequest(ApiEndpoint.LIST_JOBS.getFullUrl(), params);
        Object dataObj = responseResult.get("data");

        if (dataObj instanceof List<?> dataList) {
            List<Map<String, Object>> result = new ArrayList<>();

            for (Object item : dataList) {
                if (item instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> itemMap = (Map<String, Object>) item;
                    result.add(itemMap);
                }
            }

            return result;
        }

        return new ArrayList<>();
    }

    private static Map<String, Object> doRequest(String url, Map<String, Object> params) {
        if (authCookie == null) {
            if (!login()) {
                return new HashMap<>();
            }
        }
        try {
            HttpRequest request = HttpRequest.post(url)
                    .header("Cookie", authCookie)
                    .form(params);  // 使用 form 方法来发送 Form-data

            try (HttpResponse response = request.execute()) {
                if (response.isOk()) {
                    return JSONUtil.parseObj(response.body());
                } else {
                    log.error("Request to XXL-Job failed. Status code: {}", response.getStatus());
                    return new HashMap<>();
                }
            }
        } catch (Exception e) {
            log.error("Error occurred while calling XXL-Job API", e);
            return new HashMap<>();
        }
    }

    @PostConstruct
    public void init() {
        adminAddress = configuredAdminAddress;
        if (login()) {
            log.info("Successfully logged in to XXL-Job");
        } else {
            log.error("Failed to login to XXL-Job during initialization");
        }
    }


    private enum ApiEndpoint {
        LOGIN(API_LOGIN),
        ADD_JOB(API_ADD_JOB),
        UPDATE_JOB(API_UPDATE_JOB),
        REMOVE_JOB(API_REMOVE_JOB),
        LIST_JOBS(API_LIST_JOBS);

        private final String path;

        ApiEndpoint(String path) {
            this.path = path;
        }

        public String getFullUrl() {
            return adminAddress + path;
        }
    }
}