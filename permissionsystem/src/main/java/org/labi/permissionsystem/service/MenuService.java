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

    /**
     * 根据用户id加载该用户的权限下所应该加载的路径
     * @return
     */
    List<Menu> getMenusByUserId();
}
