package org.labi.permissionsystem.controller.SystemManagement;

import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.bean.beanTools.UserRolesTool;
import org.labi.permissionsystem.service.RoleService;
import org.labi.permissionsystem.service.UserService;
import org.labi.permissionsystem.utils.CharacterBean;
import org.labi.permissionsystem.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 操作员管理
 * @author: dzp
 * @create: 2021-03-03 10:09
 **/
@RestController
@RequestMapping("/system/operator")
public class SystemOperatorController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 获取用户及其所拥有的角色
     *
     * @param keywords
     * @return
     */
    @GetMapping("/")
    public List<UserRolesTool> getAllUserWithRoles(@RequestParam(value = "keywords", required = false) String keywords) {
        return userService.getAllUserWithRoles(keywords);
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    @GetMapping("/roles")
    public List<Role> getAllRole() {
        return roleService.getAllRoles();
    }

    /**
     * 修改用户所有有的角色
     *
     * @param userId
     * @param rids
     * @return
     */
    @PutMapping("/update")
    public RespBean updateUserRole(Integer userId, Integer[] rids) {
        if (roleService.updateUserRoles(userId, rids)) {
            return RespBean.ok(CharacterBean.success);
        }
        return RespBean.error(CharacterBean.FAILURE);
    }
}
