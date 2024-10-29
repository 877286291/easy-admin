package top.houyuji.common.mybatis.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import top.houyuji.common.mybatis.handler.InjectionMetaObjectHandler;

@AutoConfiguration(before = {MybatisPlusAutoConfiguration.class})
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(
                paginationInnerInterceptor()
        );
        return interceptor;
    }

    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor interceptor = new PaginationInnerInterceptor();
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        interceptor.setMaxLimit(-1L);
        // 分页合理化
        interceptor.setOverflow(true);
        return interceptor;
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new InjectionMetaObjectHandler();
    }
}
