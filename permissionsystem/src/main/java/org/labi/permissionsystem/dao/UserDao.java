package org.labi.permissionsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.bean.User;
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
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(@Param("userId") int userId);
}
