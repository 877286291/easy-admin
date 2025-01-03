package top.houyuji.code.generator.core.handler;

import jakarta.validation.constraints.NotNull;
import top.houyuji.code.generator.core.handler.columntype.IColumnType;

/**
 * 字段类型
 */
public interface IColumnTypeConvertHandler {

    /**
     * 执行类型转换
     *
     * @param fieldType 字段类型
     * @return ignore
     */
    IColumnType processTypeConvert(@NotNull String fieldType);

}
