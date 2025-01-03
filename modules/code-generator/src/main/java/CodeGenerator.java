import top.houyuji.code.generator.core.config.ConfigBuilder;
import top.houyuji.code.generator.core.config.GlobalConfig;
import top.houyuji.code.generator.core.config.PackageConfig;
import top.houyuji.code.generator.core.config.StrategyConfig;
import top.houyuji.code.generator.core.engine.FreemarkerTemplateEngine;
import top.houyuji.code.generator.core.handler.columntype.MysqlColumnTypeConvert;
import top.houyuji.code.generator.core.handler.keywords.MySqlKeyWordsHandler;

public class CodeGenerator {
    public static void main(String[] args) {
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .author("houyuji") // 作者
                .outputDir("D://") // 输出目录
                .commentDate("2024-05-30") // 日期
                .springdoc(true).build(); // 是否生成springdoc注解

        StrategyConfig strategyConfig = new StrategyConfig.Builder()
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
                .build(); // 构建

        PackageConfig packageConfigBuilder = new PackageConfig.Builder().build();
        ConfigBuilder configBuilder = new ConfigBuilder(strategyConfig, globalConfig, packageConfigBuilder);
        FreemarkerTemplateEngine templateEngine = new FreemarkerTemplateEngine();
//        AutoGenerator autoGenerator = new AutoGenerator().configBuilder(configBuilder)
//                .execute(tableInfos, templateEngine);
    }
}
