package org.labi.permissionsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.labi.permissionsystem.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName UserDao.java
 * @Description 用户Dao
 * @createTime 2021年01月26日 10:14:00
 */
@Repository
public interface UserDao extends BaseMapper<User> {
}
