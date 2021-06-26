package com.labi.httpclient.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-03 10:54
 **/
@Getter
@Setter
public class HttpResponse {
    private Integer statusCode;
    private String body;
}
