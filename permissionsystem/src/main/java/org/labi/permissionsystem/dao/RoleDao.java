package org.labi.permissionsystem.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
     * @param roleId
     */
    void addUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
}
