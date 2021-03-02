package org.labi.permissionsystem.controller.SystemManagement;

import org.labi.permissionsystem.bean.Menu;
import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.service.MenuService;
import org.labi.permissionsystem.service.RoleService;
import org.labi.permissionsystem.utils.CharacterBean;
import org.labi.permissionsystem.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 基础信息设置
 * @author: dzp
 * @create: 2021-02-23 15:51
 **/
@RestController
@RequestMapping("/system/basic")
public class SystemBasicController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

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

    /**
     * 获取所有菜单及其子菜单
     *
     * @return
     */
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    /**
     * 获取该角色id所对应的所有菜单id
     *
     * @param rid
     * @return
     */
    @GetMapping("/getMenuIds/{rid}")
    public List<Integer> getMenuIdsByRid(@PathVariable Integer rid) {
        return menuService.getMenuIdsByRid(rid);
    }

    /**
     * 修改角色可访问的菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    @PutMapping("/update")
    public RespBean updateMidsByRid(Integer rid, Integer[] mids) {
        if (menuService.updateMidsByRid(rid, mids)) {
            return RespBean.ok(CharacterBean.success);
        }
        return RespBean.error(CharacterBean.FAILURE);
    }
}
