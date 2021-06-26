package com.labi.crawler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-04 16:28
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statement {
    private String id;
    private String meaning;

    @Override
    public String toString() {
        return "Statement{" +
                "meaning='" + meaning + '\'' +
                '}';
    }
}
