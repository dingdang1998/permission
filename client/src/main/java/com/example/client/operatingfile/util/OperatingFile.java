package com.example.client.operatingfile.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: client
 * @description: 操作文件
 * @author: dzp
 * @create: 2021-07-22 15:07
 **/
public class OperatingFile {

    private static final Logger logger = LoggerFactory.getLogger(OperatingFile.class);

    /**
     * 读取文件内容
     */
    public static String[] readFile(String filePath) {
        FileInputStream input = null;
        FileChannel inChannel = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //设置缓冲区大小
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            input = new FileInputStream(filePath);
            inChannel = input.getChannel();

            while (inChannel.read(buffer) != -1) {
                //读到写
                buffer.flip();
                byte[] bs = new byte[buffer.limit()];
                buffer.get(bs);
                stringBuilder.append(new String(bs));
                //写到读
                buffer.clear();
            }
            return stringBuilder.toString().split("\r\n");
        } catch (Exception e) {
            logger.error("读取文件出错---" + e.getMessage(), e);
            return null;
        } finally {
            try {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                logger.error("关闭资源出错---" + e.getMessage(), e);
            }
        }
    }
}
