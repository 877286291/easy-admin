package top.houyuji.common.cache.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.houyuji.common.cache.core.EasyAdminCache;
import top.houyuji.common.cache.core.ICache;


@Configuration
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnProperty(prefix = "easy.admin.cache", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CacheAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public EasyAdminCache cache(
            ICache redisCache,
            CacheProperties cacheProperties) {
        EasyAdminCache easyAdminCache = new EasyAdminCache();
        easyAdminCache.setCache(redisCache);
        easyAdminCache.setPrefix(cacheProperties.getPrefix());
        return easyAdminCache;
    }
}