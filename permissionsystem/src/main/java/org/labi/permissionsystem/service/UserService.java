package org.labi.permissionsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.labi.permissionsystem.bean.User;
import org.labi.permissionsystem.bean.UserRoles;
import org.labi.permissionsystem.bean.beanTools.UserExportBean;

import java.util.List;

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
     *
     * @param page
     * @param size
     * @param name
     * @return
     */
    IPage<User> getUsersToPage(int page, int size, String name);

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     */
    void updateUserStatus(int id, int status);

    /**
     * admin添加用户
     *
     * @param userRoles
     */
    void addUserByAdmin(UserRoles userRoles);

    /**
     * admin编辑用户信息
     * @param userRoles
     */
    void updateUserByAdmin(UserRoles userRoles);

    /**
     * admin删除用户
     * @param userId
     */
    void deleteUserByAdmin(int userId);

    /**
     * 导出查询
     * @return
     * @param name
     */
    List<UserExportBean> getUsersToExport(String name);
}
