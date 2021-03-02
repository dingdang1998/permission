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
     *
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 根据用户id加载该用户的角色下所应该加载的路径
     *
     * @return
     */
    List<Menu> getMenusByUserId();

    /**
     * 根据角色id获取该角色所对应的菜单
     *
     * @param rid
     * @return
     */
    List<Integer> getMenuIdsByRid(Integer rid);

    /**
     * 查询所有菜单及其子菜单
     *
     * @return
     */
    List<Menu> getAllMenus();

    /**
     * 修改角色可访问的菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    boolean updateMidsByRid(Integer rid, Integer[] mids);
}
