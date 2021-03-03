package org.labi.permissionsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.bean.User;
import org.labi.permissionsystem.bean.UserRoles;
import org.labi.permissionsystem.bean.beanTools.UserExportBean;
import org.labi.permissionsystem.bean.beanTools.UserRolesTool;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName UserDao.java
 * @Description 用户Dao
 * @createTime 2021年01月26日 10:14:00
 */
@Repository
public interface UserDao extends BaseMapper<User> {
    /**
     * 查询用户所具有的角色
     *
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(@Param("userId") int userId);

    /**
     * 导出查询
     *
     * @param name
     * @return
     */
    List<UserExportBean> getUsersToExport(@Param("name") String name);

    /**
     * 查询所有用户及其所拥有的角色
     *
     * @param userId
     * @param name
     * @return
     */
    List<UserRolesTool> getAllUserWithRoles(@Param("userId") Integer userId, @Param("name") String name);
}
