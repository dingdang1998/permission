package org.labi.permissionsystem.service;

import org.labi.permissionsystem.bean.Role;

import java.util.List;

/**
 * 角色Service
 *
 * @author dzp
 */
public interface RoleService {
    /**
     * 添加用户--角色关系
     *
     * @param userId
     * @param roleIds
     */
    Integer addUserRole(int userId, Integer[] roleIds);

    /**
     * 删除用户--角色关系
     *
     * @param userId
     */
    void deleteUserRoleByUserId(int userId);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Role> getAllRoles();

    /**
     * 添加角色
     *
     * @param role
     */
    void addRole(Role role);

    /**
     * 修改用户所拥有的角色
     *
     * @param userId
     * @param rids
     * @return
     */
    boolean updateUserRoles(Integer userId, Integer[] rids);
}
