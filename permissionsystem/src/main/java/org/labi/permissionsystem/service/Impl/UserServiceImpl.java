package org.labi.permissionsystem.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.labi.permissionsystem.bean.User;
import org.labi.permissionsystem.dao.UserDao;
import org.labi.permissionsystem.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description 用户服务实现类
 * @createTime 2021年01月26日 10:11:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService, UserDetailsService {

    @Override
    public void add() {
        User user = new User();
        user.setName("赵一一");
        user.setUsername("zhaoyiyi");
        user.setPassword("zhaoyiyi");
        user.setEnabled(User.ENABLE);
        save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return null;
    }
}
