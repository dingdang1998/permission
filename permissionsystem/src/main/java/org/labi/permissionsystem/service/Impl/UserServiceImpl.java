package org.labi.permissionsystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.bean.User;
import org.labi.permissionsystem.bean.UserRoles;
import org.labi.permissionsystem.dao.UserDao;
import org.labi.permissionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description 用户服务实现类
 * @createTime 2021年01月26日 10:11:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

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
        //查找该用户名是否存在
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        User user = getOne(lambdaQueryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        //匹配该用户的角色
        List<Role> roles = userDao.getRolesByUserId(user.getId());
        //封装成UserRoles
        UserRoles userRoles = new UserRoles();
        userRoles.setEnabled(user.getEnabled());
        userRoles.setRoles(roles);
        userRoles.setUsername(user.getUsername());
        userRoles.setPassword(user.getPassword());
        return userRoles;
    }
}
