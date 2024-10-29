package top.houyuji.common.mybatis.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "easy.admin.tenant")
public class TenantProperties implements Serializable {
    /**
     * 是否启用租户过滤器,
     */
    private boolean enableTenantInterceptor = false;
    /**
     * 非租户表
     */
    private List<String> ignoreTables = List.of(
            "sys_permission",
            "sys_product",
            "sys_product_permission",
            "sys_role",
            "sys_role_permission",
            "sys_user",
            "sys_user_role",
            "sys_notice",
            "sys_quartz_job",
            "sys_oss_config",
            "bas_user_role",
            "bas_role_permission",
            "bas_permission",
            "bas_organization",
            "bas_user",
            "bas_notice_record"
    );

    /**
     * 租户字段
     */
    private String tenantColumn = "sys_code";
}