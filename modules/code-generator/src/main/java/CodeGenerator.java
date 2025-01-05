import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.query.SQLQuery;
import top.houyuji.code.generator.core.AutoGenerator;
import top.houyuji.code.generator.core.config.ConfigBuilder;
import top.houyuji.code.generator.core.config.GlobalConfig;
import top.houyuji.code.generator.core.config.PackageConfig;
import top.houyuji.code.generator.core.config.StrategyConfig;
import top.houyuji.code.generator.core.engine.FreemarkerTemplateEngine;
import top.houyuji.code.generator.core.handler.columntype.MysqlColumnTypeConvert;
import top.houyuji.code.generator.core.handler.keywords.MySqlKeyWordsHandler;
import top.houyuji.code.generator.core.po.TableField;
import top.houyuji.code.generator.core.po.TableInfo;
import top.houyuji.code.generator.jdbc.DatabaseMetaDataWrapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CodeGenerator {
    public static void main(String[] args) {
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        Connection conn = dataSourceConfig.getConn();
        GlobalConfig globalConfig = getGlobalConfig();
        StrategyConfig strategyConfig = getStrategyConfig();
        PackageConfig packageConfig = getPackageConfig();
        ConfigBuilder configBuilder = new ConfigBuilder(strategyConfig, globalConfig, packageConfig);
        FreemarkerTemplateEngine templateEngine = new FreemarkerTemplateEngine();
        List<TableInfo> tableInfos = new ArrayList<>();
        DatabaseMetaDataWrapper databaseMetaDataWrapper = getDatabaseMetaDataWrapper(conn, configBuilder, tableInfos);
        AutoGenerator autoGenerator = new AutoGenerator().configBuilder(configBuilder);
        autoGenerator.execute(tableInfos, templateEngine);
        databaseMetaDataWrapper.closeConnection();
    }

    private static DatabaseMetaDataWrapper getDatabaseMetaDataWrapper(Connection conn, ConfigBuilder configBuilder, List<TableInfo> tableInfos) {
        DatabaseMetaDataWrapper databaseMetaDataWrapper = new DatabaseMetaDataWrapper(conn, "");
        List<DatabaseMetaDataWrapper.Table> tables = databaseMetaDataWrapper.getTables();
        tables.forEach(table -> {
            String tableName = table.getName();
            TableInfo tableInfo = new TableInfo(configBuilder, tableName);
            Map<String, DatabaseMetaDataWrapper.Column> columnInfo = databaseMetaDataWrapper.getColumnInfo(tableName);
            columnInfo.forEach((columnName, column) -> {
                TableField tableField = new TableField(configBuilder, columnName);
                tableField.setType(column.getType());
                tableField.setPk(column.isPrimaryKey());
                tableField.setRequired(!column.isNullable());
                tableField.setComment(column.getComment());
                tableInfo.addField(tableField);
            });
            tableInfo.processTable();
            tableInfos.add(tableInfo);
        });
        return databaseMetaDataWrapper;
    }

    /**
     * 获取数据源
     *
     * @return 返回sql数据源
     */
    private static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig.Builder("jdbc:mysql://47.95.214.75:3306/easy_admin?serverTimezone=Asia/Shanghai&characterEncoding=utf8&autoReconnect=true",
                "root", "Hyj877286291")
                .databaseQueryClass(SQLQuery.class)
                .build();
    }

    private static PackageConfig getPackageConfig() {
        return new PackageConfig.Builder().build();
    }

    private static GlobalConfig getGlobalConfig() {
        return new GlobalConfig.Builder()
                .author("houyuji") // 作者
                .outputDir("D://") // 输出目录
                .commentDate("2024-05-30") // 日期
                .springdoc(true)
                .entityLombokModel(true) // 是否使用lombok
                .build();
    }

    private static StrategyConfig getStrategyConfig() {
        return new StrategyConfig.Builder()
                .addTablePrefix("t_") // 去除表前缀
                .entityStrategyBuilder() // 实体类策略
                .enableFileOverride() // 是否覆盖文件
                .keyWordsHandler(new MySqlKeyWordsHandler()) // 关键字处理
                .columnTypeConvertHandler(new MysqlColumnTypeConvert()) // 数据库类型转换
                .controllerStrategyBuilder() // controller策略
                .enableFileOverride() // 是否覆盖文件
                .repositoryStrategyBuilder() // repository策略
                .enableFileOverride() // 是否覆盖文件
                .serviceStrategyBuilder() // service策略
                .enableFileOverride() // 是否覆盖文件
                .disableGenerate() // 是否生成
                .serviceImplStrategyBuilder() // impl策略
                .enableFileOverride() // 是否覆盖文件
                .dtoStrategyBuilder() // dto策略
                .enableFileOverride() // 是否覆盖文件
                .chainModel(false) // 是否链式模型
                .queryStrategyBuilder() // query策略
                .enableFileOverride() // 是否覆盖文件
                .build();
    }

    /**
     * 读取控制台内容信息
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String next = scanner.next();
            if (StrUtil.isNotEmpty(next)) {
                return next;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
