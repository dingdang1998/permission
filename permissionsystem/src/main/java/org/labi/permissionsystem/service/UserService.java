package org.labi.permissionsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.labi.permissionsystem.bean.User;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description 用户服务接口
 * @createTime 2021年01月26日 10:09:00
 */
public interface UserService extends IService<User> {
    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return
     */
    boolean checkUsername(String username);

    /**
     * 分页查询用户
     * @return
     * @param page
     * @param size
     */
    IPage<User> getUsersToPage(int page,int size,String name);
}
