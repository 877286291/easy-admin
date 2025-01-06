package top.houyuji.code.generator.core.config.strategy;

import lombok.Getter;
import top.houyuji.code.generator.core.config.ITemplate;
import top.houyuji.code.generator.core.config.StrategyConfig;
import top.houyuji.code.generator.core.enums.ConstVal;
import top.houyuji.code.generator.core.handler.ConverterFileName;
import top.houyuji.code.generator.core.po.TableInfo;
import top.houyuji.common.base.utils.ClassUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * service 策略
 */
@Getter
public class ServiceStrategy implements ITemplate, IStrategy {
    /**
     * 模板路径
     */
    private String javaTemplate = ConstVal.SERVICE_TEMPLATE;

    /**
     * 父类class,带包名
     */
    private String superClass;

    /**
     * 是否生成service
     */
    private boolean generate = true;

    /**
     * 转换输出Service文件名称
     */
    private ConverterFileName converterFileName = (entityName -> entityName + ConstVal.SERVICE);
    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("superServiceClassPackage", superClass);
        data.put("superServiceClass", ClassUtil.getSimpleName(superClass));
        data.put("generateService", generate);
        return data;
    }

    public static class Builder extends BaseBuilder {
        private final ServiceStrategy serviceStrategy = new ServiceStrategy();

        public Builder(StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        /**
         * 设置模板路径
         *
         * @param javaTemplate 模板路径
         * @return this
         */
        public Builder javaTemplate(String javaTemplate) {
            this.serviceStrategy.javaTemplate = javaTemplate;
            return this;
        }


        /**
         * 设置父类
         *
         * @param superClass 父类
         * @return this
         */
        public Builder superClass(Class<?> superClass) {
            return superClass(superClass.getName());
        }

        /**
         * 设置父类
         *
         * @param superClass 父类
         * @return this
         */
        public Builder superClass(String superClass) {
            this.serviceStrategy.superClass = superClass;
            return this;
        }

        /**
         * 设置转换输出Service文件名称
         *
         * @param converterFileName 转换输出Service文件名称
         * @return this
         */
        public Builder converterFileName(ConverterFileName converterFileName) {
            this.serviceStrategy.converterFileName = converterFileName;
            return this;
        }

        /**
         * 允许覆盖文件
         *
         * @return this
         */
        public Builder enableFileOverride() {
            this.serviceStrategy.fileOverride = true;
            return this;
        }

        /**
         * 禁止覆盖文件
         *
         * @return this
         */
        public Builder disableFileOverride() {
            this.serviceStrategy.fileOverride = false;
            return this;
        }

        /**
         * 允许生成
         *
         * @return this
         */
        public Builder enableGenerate() {
            return generate(true);
        }

        /**
         * 禁止生成
         *
         * @return this
         */
        public Builder disableGenerate() {
            return generate(false);
        }

        /**
         * 是否生成
         *
         * @param generate .
         * @return .
         */
        public Builder generate(boolean generate) {
            this.serviceStrategy.generate = generate;
            return this;
        }


        public ServiceStrategy get() {
            return this.serviceStrategy;
        }
    }
}
