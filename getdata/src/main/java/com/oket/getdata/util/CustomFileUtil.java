package com.oket.getdata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @program: getdata
 * @description: 自定义操作文件工具
 * @author: dzp
 * @create: 2021-05-12 19:28
 **/
public class CustomFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(CustomFileUtil.class);

    /**
     * 关闭资源
     *
     * @param fileChannel
     * @param inputStream
     * @param outputStream
     */
    public static void closeResource(FileChannel fileChannel, FileInputStream inputStream, FileOutputStream outputStream) {
        try {
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e) {
            logger.error("关闭文件资源出错" + e);
        }
    }
}
