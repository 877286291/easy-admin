package top.houyuji.code.generator.core.config.strategy;

import top.houyuji.code.generator.core.config.IBuilder;
import top.houyuji.code.generator.core.config.StrategyConfig;


public abstract class BaseBuilder implements IBuilder<StrategyConfig> {
    private final StrategyConfig strategyConfig;

    public BaseBuilder(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }


    /**
     * entity 策略
     *
     * @return entity 策略
     */
    public EntityStrategy.Builder entityStrategyBuilder() {
        return this.strategyConfig.getEntityStrategyBuilder();
    }

    /**
     * dto 策略
     *
     * @return dto 策略
     */
    public DtoStrategy.Builder dtoStrategyBuilder() {
        return this.strategyConfig.getDtoStrategyBuilder();
    }

    /**
     * query 策略
     *
     * @return query 策略
     */
    public QueryStrategy.Builder queryStrategyBuilder() {
        return this.strategyConfig.getQueryStrategyBuilder();
    }

    /**
     * service 策略
     *
     * @return service 策略
     */
    public ServiceStrategy.Builder serviceStrategyBuilder() {
        return this.strategyConfig.getServiceStrategyBuilder();
    }


    /**
     * mapper 策略
     *
     * @return mapper 策略
     */
    public MapperStrategy.Builder mapperStrategyBuilder() {
        return this.strategyConfig.getMapperStrategyBuilder();
    }

    /**
     * mapper xml 策略
     *
     * @return mapper xml 策略
     */
    public MapperXmlStrategy.Builder mapperXmlStrategyBuilder() {
        return this.strategyConfig.getMapperXmlStrategyBuilder();
    }

    /**
     * controller 策略
     *
     * @return controller 策略
     */
    public ControllerStrategy.Builder controllerStrategyBuilder() {
        return this.strategyConfig.getControllerStrategyBuilder();
    }

    @Override
    public StrategyConfig build() {
        this.strategyConfig.validate();
        return this.strategyConfig;
    }
}
