package top.houyuji.code.generator.core.output.impl;

import top.houyuji.code.generator.core.config.strategy.IStrategy;
import top.houyuji.code.generator.core.engine.AbstractTemplateEngine;
import top.houyuji.code.generator.core.enums.OutputFile;
import top.houyuji.code.generator.core.output.AbstractOutput;

import java.io.File;


public class ControllerOutput extends AbstractOutput {
    private final AbstractTemplateEngine engine;

    public ControllerOutput(AbstractTemplateEngine engine) {
        super(engine);
        this.engine = engine;
    }


    @Override
    protected OutputFile getOutputFile() {
        return OutputFile.controller;
    }

    @Override
    protected String getFilePath(String entityName) {
        String filename = this.getStrategyConfig().getConverterFileName().converter(entityName);
        return getPathInfo(OutputFile.controller) + File.separator + filename + ".java";
    }

    @Override
    protected String getTemplatePath() {
        return this.engine.templateFilePath(
                getStrategyConfig().getJavaTemplate()
        );
    }

    @Override
    protected IStrategy getStrategyConfig() {
        return this.engine
                .getConfigBuilder()
                .getStrategyConfig()
                .getControllerStrategy();
    }
}
