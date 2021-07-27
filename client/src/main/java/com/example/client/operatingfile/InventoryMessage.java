package com.example.client.operatingfile;

import com.example.client.entity.Message;
import com.example.client.operatingfile.superior.GetMessage;
import com.example.client.operatingfile.util.OperatingFile;

import java.util.List;

/**
 * @program: client
 * @description:罐存数据
 * @author: dzp
 * @create: 2021-07-22 18:31
 **/
public class InventoryMessage extends GetMessage {

    public static final String INVENTORY_HEADER = "/inventory/header.txt";
    public static final String INVENTORY_CONTENT = "/inventory/content.txt";

    public InventoryMessage(String path) {
        super(path);
    }

    @Override
    public List<Message> getMessages() {
        return super.getMessages();
    }

    @Override
    public String[] getHeader(String path) {
        String filePath = path + INVENTORY_HEADER;
        return OperatingFile.readFile(filePath);
    }

    @Override
    public String[] getContent(String path) {
        String filePath = path + INVENTORY_CONTENT;
        return OperatingFile.readFile(filePath);
    }
}
