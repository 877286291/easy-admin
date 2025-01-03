package top.houyuji.code.generator.core.handler;

/**
 * 转换输出文件名称
 */
@FunctionalInterface
public interface ConverterFileName {
    /**
     * 转换文件名称
     *
     * @param entityName 实体名称
     * @return 文件名称
     */
    String converter(String entityName);
}
