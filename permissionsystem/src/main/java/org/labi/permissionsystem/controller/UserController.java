package org.labi.permissionsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.labi.permissionsystem.bean.RespPageBean;
import org.labi.permissionsystem.bean.User;
import org.labi.permissionsystem.bean.UserRoles;
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
     * 用户注册
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

    /**
     * 用户信息分页展示
     *
     * @return
     */
    @GetMapping("/getUsersToPage")
    public RespPageBean getUsersToPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                       @RequestParam(value = "name", required = false) String name) {
        //分页查询
        IPage<User> usersToPage = userService.getUsersToPage(page, size, name);
        //封装成RespPageBean返回
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setTotal(usersToPage.getTotal());
        respPageBean.setData(usersToPage.getRecords());
        return respPageBean;
    }

    /**
     * 修改用户状态
     *
     * @param id     用户表主键id
     * @param status 用户状态
     * @return
     */
    @PutMapping("/updateUserStatus/{id}/{status}")
    public RespBean updateUserStatus(@PathVariable int id,
                                     @PathVariable int status) {
        userService.updateUserStatus(id, status);
        return RespBean.ok(CharacterBean.success);
    }

    /**
     * admin添加用户
     *
     * @param userRoles
     * @return
     */
    @PostMapping("/adminAdd")
    public RespBean addUserByAdmin(@RequestBody UserRoles userRoles) {
        userService.addUserByAdmin(userRoles);
        return RespBean.ok(CharacterBean.success);
    }

    /**
     * admin编辑用户信息
     *
     * @param userRoles
     * @return
     */
    @PutMapping("/compile")
    public RespBean updateUserByAdmin(@RequestBody UserRoles userRoles) {
        userService.updateUserByAdmin(userRoles);
        return RespBean.ok(CharacterBean.success);
    }

    /**
     * admin删除用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/delete/{userId}")
    public RespBean deleteUserByAdmin(@PathVariable int userId) {
        userService.deleteUserByAdmin(userId);
        return RespBean.ok(CharacterBean.success);
    }
}
