package top.houyuji.common.mybatis.tenant.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.houyuji.common.mybatis.config.MybatisPlusConfig;
import top.houyuji.common.mybatis.tenant.handler.PlusTenantLineHandler;

import java.util.List;

@EnableConfigurationProperties(TenantProperties.class)
@Configuration
@AutoConfiguration(after = {MybatisPlusConfig.class})
public class MybatisTenantConfig {

    public MybatisTenantConfig(MybatisPlusInterceptor mybatisPlusInterceptor,
                               TenantProperties tenantProperties) {
        List<InnerInterceptor> interceptors = mybatisPlusInterceptor.getInterceptors();
        // 多租户插件 必须放到第一位
        interceptors.add(tenantLineInnerInterceptor(tenantProperties));
        interceptors.addAll(mybatisPlusInterceptor.getInterceptors());
        mybatisPlusInterceptor.setInterceptors(interceptors);
    }

    /**
     * 多租户插件
     */
    public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantProperties tenantProperties) {
        return new TenantLineInnerInterceptor(new PlusTenantLineHandler(tenantProperties));
    }

}


