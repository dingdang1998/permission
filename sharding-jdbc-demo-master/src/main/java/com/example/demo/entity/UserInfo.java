package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息
 *
 * @author dzp
 */
@Getter
@Setter
@ToString
public class UserInfo {

    private Long userId;

    private String userName;

    private String account;

    private String password;
}
