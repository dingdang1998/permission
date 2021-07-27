package com.example.client.operatingfile;

import com.example.client.entity.Message;
import com.example.client.operatingfile.superior.GetMessage;
import com.example.client.operatingfile.util.OperatingFile;

import java.util.List;

/**
 * @program: client
 * @description:报警数据
 * @author: dzp
 * @create: 2021-07-22 18:31
 **/
public class SensorMessage extends GetMessage {

    public static final String SENSOR_HEADER = "/sensor/header.txt";
    public static final String SENSOR_CONTENT = "/sensor/content.txt";

    public SensorMessage(String path) {
        super(path);
    }

    @Override
    public List<Message> getMessages() {
        return super.getMessages();
    }

    @Override
    public String[] getHeader(String path) {
        String filePath = path + SENSOR_HEADER;
        return OperatingFile.readFile(filePath);
    }

    @Override
    public String[] getContent(String path) {
        String filePath = path + SENSOR_CONTENT;
        return OperatingFile.readFile(filePath);
    }
}
