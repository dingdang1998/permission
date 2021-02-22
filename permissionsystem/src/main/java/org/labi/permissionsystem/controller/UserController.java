package org.labi.permissionsystem.controller;

import org.labi.permissionsystem.bean.User;
import org.labi.permissionsystem.service.UserService;
import org.labi.permissionsystem.utils.CharacterBean;
import org.labi.permissionsystem.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public RespBean addUser(@RequestBody User user) {
        userService.addUser(user);
        return RespBean.ok(CharacterBean.registerSuccess);
    }

    /**
     * 检查用户名是否存在
     *
     * @return
     */
    @GetMapping("/checkUsername")
    public boolean checkUsername(@RequestParam(value = "username") String username) {
        return userService.checkUsername(username);
    }
}
