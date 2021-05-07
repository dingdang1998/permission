package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: demo
 * @description: 学生信息
 * @author: dzp
 * @create: 2021-05-07 09:38
 **/
@Getter
@Setter
@ToString
public class StudentInfo {

    private Long studentId;

    private String studentName;

    private String account;

    private String password;
}
