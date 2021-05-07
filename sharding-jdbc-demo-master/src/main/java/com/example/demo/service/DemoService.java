package com.example.demo.service;


import com.example.demo.entity.StudentInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.StudentInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 */
@Slf4j
@Service
public class DemoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private StudentInfoMapper studentInfoMapper;

    public static Long userId = 150L;
    public static Long studentId = 150L;

    public void demo() {
        System.out.println("Insert--------------");
        for (int i = 1; i <= 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setAccount(String.valueOf(i));
            userInfo.setPassword("user_pass" + i);
            userInfo.setUserName("user_name" + i);
            userId++;
            userInfoMapper.insert(userInfo);

            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setStudentId(studentId);
            studentInfo.setAccount(String.valueOf(i));
            studentInfo.setPassword("student_" + i);
            studentInfo.setStudentName("student_" + i);
            studentId++;
            studentInfoMapper.insert(studentInfo);
        }
        System.out.println("..........");

    }
}
