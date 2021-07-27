package com.example.client.operatingfile.superior;

import com.example.client.entity.Message;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: client
 * @description:
 * @author: dzp
 * @create: 2021-07-22 16:56
 **/
@AllArgsConstructor
public abstract class GetMessage implements GetData {

    private final String path;

    /**
     * 获取Message对象
     *
     * @return
     */
    protected List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        String[] header = getHeader(this.path);
        String[] content = getContent(this.path);
        //拼接Message
        if (header != null && header.length > 0 && content != null && content.length > 0) {
            for (String s : header) {
                for (String value : content) {
                    Message message = new Message(s, value);
                    messages.add(message);
                }
            }
            return messages;
        }
        return null;
    }
}
