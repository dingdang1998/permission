package com.example.client.operatingfile;

import com.example.client.entity.Message;
import com.example.client.operatingfile.superior.GetMessage;
import com.example.client.operatingfile.util.OperatingFile;

import java.util.List;

/**
 * @program: client
 * @description:卸油数据
 * @author: dzp
 * @create: 2021-07-22 18:31
 **/
public class DeliveryMessage extends GetMessage {

    public static final String DELIVERY_HEADER = "/delivery/header.txt";
    public static final String DELIVERY_CONTENT = "/delivery/content.txt";

    public DeliveryMessage(String path) {
        super(path);
    }

    @Override
    public List<Message> getMessages() {
        return super.getMessages();
    }

    @Override
    public String[] getHeader(String path) {
        String filePath = path + DELIVERY_HEADER;
        return OperatingFile.readFile(filePath);
    }

    @Override
    public String[] getContent(String path) {
        String filePath = path + DELIVERY_CONTENT;
        return OperatingFile.readFile(filePath);
    }
}
