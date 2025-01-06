package top.houyuji.code.generator.core.output.impl;

import top.houyuji.code.generator.core.config.strategy.IStrategy;
import top.houyuji.code.generator.core.engine.AbstractTemplateEngine;
import top.houyuji.code.generator.core.enums.OutputFile;
import top.houyuji.code.generator.core.output.AbstractOutput;

import java.io.File;

/**
 * Mapper 输出
 */
public class MapperOutput extends AbstractOutput {
    private final AbstractTemplateEngine engine;

    public MapperOutput(AbstractTemplateEngine engine) {
        super(engine);
        this.engine = engine;
    }

    @Override
    protected OutputFile getOutputFile() {
        return OutputFile.mapper;
    }

    @Override
    protected String getFilePath(String entityName) {
        String filename = this.getStrategyConfig().getConverterFileName().converter(entityName);
        String pathInfo = getPathInfo(getOutputFile());
        return pathInfo + File.separator + filename + ".java";
    }

    @Override
    protected String getTemplatePath() {
        return engine.templateFilePath(getStrategyConfig().getJavaTemplate());
    }

    @Override
    protected IStrategy getStrategyConfig() {
        return engine
                .getConfigBuilder()
                .getStrategyConfig()
                .getMapperStrategy();
    }
}
