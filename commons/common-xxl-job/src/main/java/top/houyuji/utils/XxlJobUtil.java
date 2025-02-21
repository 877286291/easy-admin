package top.houyuji.utils;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class XxlJobUtil {

    private static String adminAddresses;
    private static String accessToken;

    public static void init(String adminAddresses, String accessToken) {
        XxlJobUtil.adminAddresses = adminAddresses;
        XxlJobUtil.accessToken = accessToken;
    }

    /**
     * 添加定时任务
     *
     * @param jobDesc         任务描述
     * @param executorHandler 执行器
     * @param cronExpression  cron表达式
     * @return 操作结果
     */
    public static ReturnT<String> addJob(String jobDesc, String executorHandler, String cronExpression) {
        String url = adminAddresses + "/jobinfo/add";

        Map<String, Object> params = new HashMap<>();
        params.put("jobDesc", jobDesc);
        params.put("executorHandler", executorHandler);
        params.put("cronExpression", cronExpression);
        params.put("executorRouteStrategy", "FIRST");
        params.put("misfireStrategy", "DO_NOTHING");
        params.put("executorBlockStrategy", "SERIAL_EXECUTION");

        return doRequest(url, params);
    }

    /**
     * 修改定时任务
     *
     * @param jobId           任务ID
     * @param jobDesc         任务描述
     * @param executorHandler 执行器
     * @param cronExpression  cron表达式
     * @return 操作结果
     */
    public static ReturnT<String> updateJob(int jobId, String jobDesc, String executorHandler, String cronExpression) {
        String url = adminAddresses + "/jobinfo/update";

        Map<String, Object> params = new HashMap<>();
        params.put("id", jobId);
        params.put("jobDesc", jobDesc);
        params.put("executorHandler", executorHandler);
        params.put("cronExpression", cronExpression);

        return doRequest(url, params);
    }

    /**
     * 删除定时任务
     *
     * @param jobId 任务ID
     * @return 操作结果
     */
    public static ReturnT<String> removeJob(int jobId) {
        String url = adminAddresses + "/jobinfo/remove";

        Map<String, Object> params = new HashMap<>();
        params.put("id", jobId);

        return doRequest(url, params);
    }

    private static ReturnT<String> doRequest(String url, Map<String, Object> params) {
        if (adminAddresses == null || accessToken == null) {
            throw new IllegalStateException("XxlJobUtil has not been initialized. Call init() first.");
        }

        try (HttpResponse response = HttpRequest.post(url)
                .header("XXL-JOB-ACCESS-TOKEN", accessToken)
                .body(JSONUtil.toJsonStr(params))
                .execute()) {

            if (response.isOk()) {
                return JSONUtil.toBean(response.body(), new TypeReference<>() {
                }, false);
            } else {
                log.error("Request to XXL-Job failed. Status code: {}", response.getStatus());
                return new ReturnT<>(ReturnT.FAIL_CODE, "Request to XXL-Job failed");
            }
        } catch (Exception e) {
            log.error("Error occurred while calling XXL-Job API", e);
            return new ReturnT<>(ReturnT.FAIL_CODE, "Error occurred while calling XXL-Job API: " + e.getMessage());
        }
    }
}