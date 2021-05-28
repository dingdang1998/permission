package com.oket.getdata.operatfile;

import com.oket.getdata.util.CustomFileUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @program: getdata
 * @description: 文件操作
 * @author: dzp
 * @create: 2021-05-13 11:38
 **/
public class OperatingFile {

    private static final Logger logger = LoggerFactory.getLogger(OperatingFile.class);

    /**
     * 将查询数据写入文件
     *
     * @param list
     */
    public static void writeQueryDataToFile(List list, String path) {
        FileOutputStream out = null;
        FileChannel outChannel = null;
        try {
            //非直接缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            out = FileUtils.openOutputStream(new File(path));
            outChannel = out.getChannel();

            for (Object object : list) {
                //往buffer中写
                buffer.put(object.toString().getBytes());
                //写到读
                buffer.flip();
                //通过channel将buffer中的数据写入文件
                outChannel.write(buffer);
                //读到下一次写，重置
                buffer.clear();
            }
        } catch (Exception e) {
            logger.error("查询数据写入文件出错" + e);
        } finally {
            CustomFileUtil.closeResource(outChannel, null, out);
        }
    }

    /**
     * 读取记录取数id的文件
     *
     * @param path
     * @return
     */
    public static String[] readIdFile(String path) {
        FileInputStream input = null;
        FileChannel inChannel = null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            input = new FileInputStream(path);
            inChannel = input.getChannel();
            //将硬盘中文件的内容写入buffer
            inChannel.read(buffer);
            //从写切换到读
            buffer.flip();
            byte[] bs = new byte[buffer.limit()];
            if (bs.length == 0) {
                return null;
            }
            buffer.get(bs);
            return new String(bs).trim().split(",");
        } catch (FileNotFoundException e) {
            //没有生成id文件
            return null;
        } catch (Exception e) {
            logger.error("读取记录取数id的文件出错" + e);
            return null;
        } finally {
            CustomFileUtil.closeResource(inChannel, input, null);
        }
    }
}
