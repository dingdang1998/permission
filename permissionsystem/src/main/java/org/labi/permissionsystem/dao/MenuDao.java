package org.labi.permissionsystem.dao;

import org.apache.ibatis.annotations.Param;
import org.labi.permissionsystem.bean.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 菜单Dao
 * @author: dzp
 * @create: 2021-02-22 15:35
 **/
@Repository
public interface MenuDao {
    /**
     * 获取所有路径及其对应的角色
     *
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     * 根据用户id加载该用户的角色下所应该加载的路径
     *
     * @param userId
     * @return
     */
    List<Menu> getMenusByUserId(@Param("userId") int userId);

    /**
     * 根据角色id查询该角色所对应的菜单ids
     *
     * @param rid
     * @return
     */
    List<Integer> getMenuIdsByRid(@Param("rid") Integer rid);

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
    Integer updateMidsByRid(@Param("rid") Integer rid, @Param("mids") Integer[] mids);

    /**
     * 删除角色--菜单关系
     * @param rid
     */
    void deleteByRid(@Param("rid") Integer rid);
}
