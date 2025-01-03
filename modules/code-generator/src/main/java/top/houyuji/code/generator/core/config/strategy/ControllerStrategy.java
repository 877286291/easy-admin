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

@Getter
public class ControllerStrategy implements ITemplate, IStrategy {
    /**
     * 模板路径
     */
    private String javaTemplate = ConstVal.CONTROLLER_TEMPLATE;
    /**
     * 父类,带包名
     */
    private String superClass;

    /**
     * 控制器文件名转换
     */
    private ConverterFileName converterFileName = (entityName -> entityName + ConstVal.CONTROLLER);

    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("superControllerClassPackage", superClass);
        data.put("superControllerClass", ClassUtil.getSimpleName(superClass));
        return data;
    }

    public static class Builder extends BaseBuilder {
        private final ControllerStrategy controllerStrategy = new ControllerStrategy();

        public Builder(StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        /**
         * 模板路径
         *
         * @param javaTemplate .
         * @return .
         */
        public Builder javaTemplate(String javaTemplate) {
            this.controllerStrategy.javaTemplate = javaTemplate;
            return this;
        }

        /**
         * 父类,带包名
         *
         * @param superClass .
         * @return .
         */
        public Builder superClass(String superClass) {
            this.controllerStrategy.superClass = superClass;
            return this;
        }

        /**
         * 控制器文件名转换
         *
         * @param converterFileName .
         * @return .
         */
        public Builder converterFileName(ConverterFileName converterFileName) {
            this.controllerStrategy.converterFileName = converterFileName;
            return this;
        }

        /**
         * 允许覆盖文件
         *
         * @return .
         */
        public Builder enableFileOverride() {
            this.controllerStrategy.fileOverride = true;
            return this;
        }

        /**
         * 禁止覆盖文件
         *
         * @return .
         */
        public Builder disableFileOverride() {
            this.controllerStrategy.fileOverride = false;
            return this;
        }


        public ControllerStrategy get() {
            return this.controllerStrategy;
        }
    }
}
