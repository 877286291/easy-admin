package top.houyuji.code.generator.core.handler.columntype;

import top.houyuji.code.generator.core.handler.IColumnTypeConvertHandler;

import java.util.AbstractMap;
import java.util.List;
import java.util.function.Function;

/**
 * Mysql字段类型转换
 */
public class MysqlColumnTypeConvert implements IColumnTypeConvertHandler {
    public static final MysqlColumnTypeConvert INSTANCE = new MysqlColumnTypeConvert();
    private final List<AbstractMap.SimpleEntry<Function<String, Boolean>, IColumnType>> converts;

    public MysqlColumnTypeConvert() {
        this.converts = List.of(
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "point"),
                        DbColumnType.BYTE_ARRAY
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "char", "text", "json", "enum"),
                        DbColumnType.STRING
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "bigint"),
                        DbColumnType.LONG
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "tinyint(1)", "bit(1)"),
                        DbColumnType.BOOLEAN
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "bit"),
                        DbColumnType.BYTE
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "int"),
                        DbColumnType.INTEGER
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "decimal"),
                        DbColumnType.BIG_DECIMAL
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "clob"),
                        DbColumnType.CLOB
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "blob"),
                        DbColumnType.BLOB
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "float"),
                        DbColumnType.FLOAT
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "double"),
                        DbColumnType.DOUBLE
                ),
                new AbstractMap.SimpleEntry<>(
                        fieldType -> contains(fieldType, "date", "time", "year"),
                        DbColumnType.DATE_SQL
                )
        );
    }

    static boolean contains(String fieldType, String... types) {
        for (String type : types) {
            if (fieldType.contains(type)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IColumnType processTypeConvert(String fieldType) {
        String type = fieldType.toLowerCase();
        return converts.stream()
                .filter(entry -> entry.getKey().apply(type))
                .map(AbstractMap.SimpleEntry::getValue)
                .findFirst()
                .orElse(DbColumnType.STRING);
    }
}
