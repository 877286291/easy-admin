package top.houyuji.code.generator.core.handler;

import top.houyuji.code.generator.core.enums.OutputFile;

import java.io.File;

/**
 * 输出文件接口
 */
public interface IOutputFile {

    /**
     * 创建文件
     *
     * @param filePath   默认文件路径
     * @param outputFile 输出文件类型
     * @return {@link File}
     */
    File createFile(String filePath, OutputFile outputFile);
}
