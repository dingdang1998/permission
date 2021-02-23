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
     * 根据用户id加载该用户的权限下所应该加载的路径
     * @return
     * @param userId
     */
    List<Menu> getMenusByUserId(@Param("userId") int userId);
}
