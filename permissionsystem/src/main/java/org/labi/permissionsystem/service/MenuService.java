package org.labi.permissionsystem.service;

import org.labi.permissionsystem.bean.Menu;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 菜单Service
 * @author: dzp
 * @create: 2021-02-22 15:30
 **/
public interface MenuService {
    /**
     * 获取所有路径及其所对应的角色
     * @return
     */
    List<Menu> getAllMenusWithRole();
}
