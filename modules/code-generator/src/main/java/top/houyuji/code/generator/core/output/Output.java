package top.houyuji.code.generator.core.output;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import top.houyuji.code.generator.core.po.TableInfo;

import java.util.Map;


public interface Output {

    /**
     * 输出文件
     *
     * @param tableInfo 表信息
     */
    default void outputFile(@Nonnull TableInfo tableInfo) {
        outputFile(tableInfo, null);
    }

    /**
     * 输出文件
     *
     * @param tableInfo 表信息
     * @param dataMap   需要渲染的数据
     */
    void outputFile(@Nonnull TableInfo tableInfo, @Nullable Map<String, Object> dataMap);


    /**
     * 输出字符串
     *
     * @param tableInfo 表信息
     */
    default String outputString(TableInfo tableInfo) {
        return outputString(tableInfo, null);
    }

    /**
     * 输出字符串
     *
     * @param tableInfo 表信息
     * @param dataMap   需要渲染的数据
     */
    String outputString(@Nonnull TableInfo tableInfo, @Nullable Map<String, Object> dataMap);
}