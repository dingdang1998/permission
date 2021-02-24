package org.labi.permissionsystem.controller;

import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.service.RoleService;
import org.labi.permissionsystem.utils.CharacterBean;
import org.labi.permissionsystem.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 角色控制层
 * @author: dzp
 * @create: 2021-02-23 15:51
 **/
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色
     *
     * @return
     */
    @GetMapping("/getAll")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @PostMapping("/add")
    public RespBean addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return RespBean.ok(CharacterBean.success);
    }
}
