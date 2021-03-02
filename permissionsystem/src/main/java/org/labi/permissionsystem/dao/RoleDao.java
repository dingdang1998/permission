package org.labi.permissionsystem.dao;

import org.apache.ibatis.annotations.Param;
import org.labi.permissionsystem.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 角色Dao
 * @author: dzp
 * @create: 2021-02-22 13:39
 **/
@Repository
public interface RoleDao {
    /**
     * 添加用户--角色关系
     *
     * @param userId
     * @param roleIds
     */
    void addUserRole(@Param("userId") int userId, @Param("roleIds") Integer[] roleIds);

    /**
     * 删除用户--角色关系
     *
     * @param userId
     */
    void deleteUserRoleByUserId(@Param("userId") int userId);

    /**
     * 获取所有角色
     * @return
     */
    List<Role> getAllRoles();

    /**
     * 添加角色
     * @param role
     */
    void addRole(Role role);
}
