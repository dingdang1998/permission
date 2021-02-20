package org.labi.permissionsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.labi.permissionsystem.bean.User;
import org.springframework.stereotype.Service;

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
     */
    void add();
}
