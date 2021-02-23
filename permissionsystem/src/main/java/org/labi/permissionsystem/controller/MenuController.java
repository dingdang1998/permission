package org.labi.permissionsystem.controller;

import org.labi.permissionsystem.bean.Menu;
import org.labi.permissionsystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 菜单控制层
 * @author: dzp
 * @create: 2021-02-23 08:50
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getMenus")
    public List<Menu> getMenusByUserId() {
        return menuService.getMenusByUserId();
    }
}
