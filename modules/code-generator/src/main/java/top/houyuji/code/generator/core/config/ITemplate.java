package top.houyuji.code.generator.core.config;

import top.houyuji.code.generator.core.po.TableInfo;

import java.util.Map;

public interface ITemplate {

    /**
     * 渲染数据
     *
     * @param tableInfo 表信息
     * @return 渲染数据
     */
    Map<String, Object> renderData(TableInfo tableInfo);
}
