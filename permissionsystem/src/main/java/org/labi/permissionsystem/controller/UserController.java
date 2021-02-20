package org.labi.permissionsystem.controller;

import org.labi.permissionsystem.service.UserService;
import org.labi.permissionsystem.utils.CharacterBean;
import org.labi.permissionsystem.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description 用户控制层
 * @createTime 2021年01月26日 10:23:00
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public RespBean add() {
        userService.add();
        return RespBean.respBean(CharacterBean.TWO_HUNDRED, CharacterBean.success, null);
    }
}
