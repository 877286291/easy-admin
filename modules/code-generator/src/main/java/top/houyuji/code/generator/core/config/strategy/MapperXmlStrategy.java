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
 * Mapper Java 生成策略
 */
@Getter
public class MapperXmlStrategy implements ITemplate, IStrategy {
    /**
     * 自定义继承的Mapper类全称，带包名
     */
    private String superClass = ConstVal.SUPER_MAPPER_CLASS;
    /**
     * Mapper 模板
     */
    private String javaTemplate = ConstVal.MAPPER_XML_TEMPLATE;
    /**
     * 转换输出文件名称
     */
    private ConverterFileName converterFileName = (entityName) -> (entityName + ConstVal.MAPPER);
    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("superMapperClassPackage", superClass);
        data.put("superMapperClass", ClassUtil.getSimpleName(superClass));
        return data;
    }

    public static class Builder extends BaseBuilder {
        private final MapperXmlStrategy mapperStrategy = new MapperXmlStrategy();

        public Builder(StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        /**
         * Mapper 模板
         *
         * @param javaTemplate 模板
         * @return this
         */
        public Builder javaTemplate(String javaTemplate) {
            mapperStrategy.javaTemplate = javaTemplate;
            return this;
        }

        /**
         * 自定义继承的Entity类全称，带包名
         *
         * @param superClass 全称
         * @return this
         */
        public Builder superClass(String superClass) {
            mapperStrategy.superClass = superClass;
            return this;
        }

        /**
         * 转换输出文件名称
         *
         * @param converterFileName 转换
         * @return this
         * @see ConverterFileName
         */
        public Builder converterFileName(ConverterFileName converterFileName) {
            mapperStrategy.converterFileName = converterFileName;
            return this;
        }

        /**
         * 覆盖已有文件
         *
         * @return this
         */
        public Builder enableFileOverride() {
            mapperStrategy.fileOverride = true;
            return this;
        }

        /**
         * 不覆盖已有文件
         *
         * @return this
         */
        public Builder disableFileOverride() {
            mapperStrategy.fileOverride = false;
            return this;
        }

        public MapperXmlStrategy get() {
            return mapperStrategy;
        }
    }
}
