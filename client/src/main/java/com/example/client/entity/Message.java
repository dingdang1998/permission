package com.example.client.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: client
 * @description: 要发送的信息
 * @author: dzp
 * @create: 2021-07-22 14:49
 **/
@Setter
@Getter
public class Message {

    /**
     * 校验域写死
     */
    public static final String CRC = "&&-&&&+>'A+&)&''";

    /**
     * 信息头
     */
    private String header;
    /**
     * 信息域
     */
    private String content;
    /**
     * 校验域
     */
    private String crc;

    public Message(String header, String content) {
        this.header = header;
        this.content = content;
        this.crc = CRC;
    }

    @Override
    public String toString() {
        return header + "\n" + content + "\n" + crc + "\n";
    }
}
