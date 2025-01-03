package top.houyuji.code.generator.core.output.impl;

import top.houyuji.code.generator.core.config.strategy.IStrategy;
import top.houyuji.code.generator.core.engine.AbstractTemplateEngine;
import top.houyuji.code.generator.core.enums.OutputFile;
import top.houyuji.code.generator.core.output.AbstractOutput;

import java.io.File;

/**
 * service 输出
 */
public class ServiceOutput extends AbstractOutput {
    private final AbstractTemplateEngine engine;

    public ServiceOutput(AbstractTemplateEngine engine) {
        super(engine);
        this.engine = engine;
    }

    @Override
    protected OutputFile getOutputFile() {
        return OutputFile.service;
    }

    @Override
    protected String getFilePath(String entityName) {
        String filename = getStrategyConfig().getConverterFileName().converter(entityName);
        return getPathInfo(getOutputFile()) + File.separator + filename + ".java";
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
                .getServiceStrategy();
    }
}
