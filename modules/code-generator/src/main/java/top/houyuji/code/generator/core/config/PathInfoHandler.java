package top.houyuji.code.generator.core.config;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import top.houyuji.code.generator.core.config.strategy.EntityStrategy;
import top.houyuji.code.generator.core.config.strategy.MapperStrategy;
import top.houyuji.code.generator.core.config.strategy.MapperXmlStrategy;
import top.houyuji.code.generator.core.config.strategy.ServiceStrategy;
import top.houyuji.code.generator.core.enums.ConstVal;
import top.houyuji.code.generator.core.enums.OutputFile;
import top.houyuji.common.base.utils.StrUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 路径信息处理
 */
public class PathInfoHandler {
    /**
     * 输出目录
     */
    private final String outputDir;

    /**
     * 包配置信息
     */
    private final PackageConfig packageConfig;

    @Getter
    private final Map<OutputFile, String> pathInfo = new HashMap<>();


    public PathInfoHandler(ConfigBuilder configBuilder) {
        this.outputDir = configBuilder.getGlobalConfig().getOutputDir();
        this.packageConfig = configBuilder.getPackageConfig();
        setDefaultPathInfo(
                configBuilder.getGlobalConfig(),
                configBuilder.getStrategyConfig()
        );
    }

    private void setDefaultPathInfo(GlobalConfig globalConfig, StrategyConfig strategyConfig) {
        //entity
        EntityStrategy entityStrategy = strategyConfig.getEntityStrategy();
        putPathInfo(entityStrategy.getJavaTemplate(), OutputFile.entity, ConstVal.PACKAGE_ENTITY);

        //dto
        putPathInfo(strategyConfig.getDtoStrategy().getJavaTemplate(), OutputFile.dto, ConstVal.PACKAGE_DTO);

        // query
        putPathInfo(strategyConfig.getQueryStrategy().getJavaTemplate(), OutputFile.query, ConstVal.PACKAGE_QUERY);

        //mapper
        MapperStrategy mapperStrategy = strategyConfig.getMapperStrategy();
        putPathInfo(mapperStrategy.getJavaTemplate(), OutputFile.mapper, ConstVal.PACKAGE_MAPPER);

        //mapper xml
        MapperXmlStrategy mapperXmlStrategy = strategyConfig.getMapperXmlStrategy();
        putPathInfo(mapperXmlStrategy.getJavaTemplate(), OutputFile.xml, ConstVal.PACKAGE_MAPPER);

        //service
        ServiceStrategy serviceStrategy = strategyConfig.getServiceStrategy();
        putPathInfo(serviceStrategy.getJavaTemplate(), OutputFile.service, ConstVal.PACKAGE_SERVICE);

        //controller
        putPathInfo(strategyConfig.getControllerStrategy().getJavaTemplate(), OutputFile.controller, ConstVal.PACKAGE_CONTROLLER);
    }

    private void putPathInfo(String template, OutputFile outputFile, String module) {
        if (StringUtils.isNotBlank(template)) {
            putPathInfo(outputFile, module);
        }
    }

    private void putPathInfo(OutputFile outputFile, String module) {
        pathInfo.putIfAbsent(outputFile, joinPath(outputDir, packageConfig.getPackage(module)));
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StrUtil.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StrUtil.BACKSLASH + File.separator);
        return parentDir + packageName;
    }
}
