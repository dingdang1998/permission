package com.labi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: microservice
 * @description: 封装实体类
 * @author: dzp
 * @create: 2021-05-18 18:58
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    /**
     * 成功或失败
     */
    private boolean flag;
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 请求的结果数据
     */
    private Object data;
}
