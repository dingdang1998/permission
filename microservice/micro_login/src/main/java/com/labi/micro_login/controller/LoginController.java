package com.labi.micro_login.controller;

import com.labi.entity.JwtUtil;
import com.labi.entity.Message;
import com.labi.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-25 14:57
 **/
@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Message login(@RequestBody Map<String, String> login) {
        //controller-service-dao 模拟操作
        String username = login.get("username");
        String password = login.get("password");
        //findUserByUsernameAndPassword
        //zs/abc -> 返回这个人的全部信息User(zs/abc,id(1001).....)
        //假设数据库中的zs/abc（并且：假设这个人的id是1001,这个人是管理员权限admin）
        if ("zs".equals(username) && "abc".equals(password)) {
            //登录成功，服务端生成token
            //String id, String subject,String roles
            String token = jwtUtil.createJwt("1001", "zs", "admin");
            //登录成功：返回token\用户名
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("username", username);
            return new Message(true, StatusCode.OK, map);
        } else {
            return new Message(false, StatusCode.ERROR, null);
        }
    }
}
