package org.labi.permissionsystem.service;

/**
 * 权限Service
 *
 * @author dzp
 */
public interface RoleService {
    /**
     * 添加用户--权限关系
     *
     * @param userId
     * @param roleId
     */
    void addUserRole(int userId, int roleId);
}
