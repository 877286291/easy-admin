package top.houyuji.code.generator.core.handler.nameconvert;

import top.houyuji.code.generator.core.handler.INameConvertHandler;
import top.houyuji.code.generator.core.handler.NamingStrategy;
import top.houyuji.code.generator.core.po.TableField;
import top.houyuji.code.generator.core.po.TableInfo;
import top.houyuji.common.base.utils.StrUtil;

import java.util.Set;

/**
 * 默认的名称转换
 */
public class DefaultNameConvert implements INameConvertHandler {
    @Override
    public String entityNameConvert(TableInfo tableInfo) {
        return NamingStrategy.capitalFirst(
                processName(tableInfo.getName(),
                        tableInfo.getEntityStrategy().getNamingStrategy(),
                        tableInfo.getStrategyConfig().getTablePrefix(),
                        tableInfo.getStrategyConfig().getTableSuffix())
        );
    }

    @Override
    public String propertyNameConvert(TableField field) {
        return processName(
                field.getName(),
                field.getEntityStrategy().columnNamingStrategy(),
                field.getStrategyConfig().getFieldPrefix(),
                field.getStrategyConfig().getFieldSuffix()
        );
    }

    private String processName(String name, NamingStrategy strategy, Set<String> prefix, Set<String> suffix) {
        String propertyName = name;
        // 删除前缀
        if (!prefix.isEmpty()) {
            propertyName = NamingStrategy.removePrefix(propertyName, prefix);
        }
        // 删除后缀
        if (!suffix.isEmpty()) {
            propertyName = NamingStrategy.removeSuffix(propertyName, suffix);
        }
        if (StrUtil.isBlank(propertyName)) {
            throw new RuntimeException(String.format("%s 的名称转换结果为空，请检查是否配置问题", name));
        }
        // 下划线转驼峰
        if (NamingStrategy.underline_to_camel.equals(strategy)) {
            return NamingStrategy.underlineToCamel(propertyName);
        }
        return propertyName;
    }
}
