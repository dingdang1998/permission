package org.labi.permissionsystem.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.bean.User;
import org.labi.permissionsystem.bean.UserRoles;
import org.labi.permissionsystem.bean.beanTools.UserExportBean;
import org.labi.permissionsystem.bean.beanTools.UserRolesTool;
import org.labi.permissionsystem.dao.UserDao;
import org.labi.permissionsystem.service.RoleService;
import org.labi.permissionsystem.service.UserService;
import org.labi.permissionsystem.utils.UserRolesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private RoleService roleService;

    @Override
    public void addUser(User user) {
        //将密码进行加密处理
        user.setPassword(passwordEncryption(user.getPassword()));
        //新注册用户默认为启用
        user.setEnabled(User.ENABLE);
        save(user);
        //添加用户--角色关系,注册的用户初始角色都为（7--普通用户）
        Integer[] roleIds = {7};
        roleService.addUserRole(user.getId(), roleIds);
    }

    @Override
    public boolean checkUsername(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        User user = getOne(lambdaQueryWrapper);
        return user != null;
    }

    @Override
    public IPage<User> getUsersToPage(int page, int size, String name) {
        IPage<User> iPage = new Page<>(page, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //排除对password的查询
        queryWrapper.select(User.class, i -> !"password".equals(i.getProperty()));
        //获取当前登录用户所具有的角色
        UserRoles userRoles = UserRolesUtils.getCurrent();
        if (!checkUserRoles(userRoles.getRoles())) {
            queryWrapper.eq("id", userRoles.getId());
        }
        if (name != null && !"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return page(iPage, queryWrapper);
    }

    @Override
    public void updateUserStatus(int id, int status) {
        User user = new User();
        user.setId(id);
        user.setEnabled(status);
        updateById(user);
    }

    @Override
    public void addUserByAdmin(UserRoles userRoles) {
        //转换成User
        User user = new User();
        user.setUsername(userRoles.getUsername());
        user.setName(userRoles.getName());
        user.setEnabled(User.ENABLE);
        user.setPassword(passwordEncryption(userRoles.getPassword()));
        //添加
        save(user);
        //增加用户--角色关联关系
        Integer[] intRoles = roleListToArray(userRoles.getRoles());
        roleService.addUserRole(user.getId(), intRoles);
    }

    @Override
    public void updateUserByAdmin(UserRoles userRoles) {
        //转换成User
        User user = new User();
        user.setId(userRoles.getId());
        user.setUsername(userRoles.getUsername());
        user.setName(userRoles.getName());
        user.setEnabled(userRoles.getEnabled());
        //修改
        updateById(user);
    }

    @Override
    public void deleteUserByAdmin(int userId) {
        //删除用户
        removeById(userId);
        //删除用户--角色关系
        roleService.deleteUserRoleByUserId(userId);
    }

    @Override
    public List<UserExportBean> getUsersToExport(String name) {
        //获取当前登录用户所具有的角色
        UserRoles userRoles = UserRolesUtils.getCurrent();
        if (!checkUserRoles(userRoles.getRoles())) {
            //没有admin角色，只能导出自己的信息
            return userDao.getUsersToExport(userRoles.getName());
        }
        //有admin角色
        if (StringUtils.isEmpty(name)) {
            name = null;
        }
        return userDao.getUsersToExport(name);
    }

    @Override
    public List<UserRolesTool> getAllUserWithRoles(String name) {
        return userDao.getAllUserWithRoles(UserRolesUtils.getCurrent().getId(), name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查找该用户名是否存在
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        User user = getOne(lambdaQueryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在，请检查用户名或注册");
        }
        //匹配该用户的角色
        List<Role> roles = userDao.getRolesByUserId(user.getId());
        //封装成UserRoles
        UserRoles userRoles = new UserRoles();
        userRoles.setId(user.getId());
        userRoles.setName(user.getName());
        userRoles.setEnabled(user.getEnabled());
        userRoles.setRoles(roles);
        userRoles.setUsername(user.getUsername());
        userRoles.setPassword(user.getPassword());
        return userRoles;
    }

    /**
     * 对密码进行加密处理
     *
     * @param password
     */
    private String passwordEncryption(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    /**
     * 检查用户是否为管理员
     */
    private boolean checkUserRoles(List<Role> roles) {
        for (Role role : roles) {
            if ("ROLE_admin".equals(role.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 提取角色id
     *
     * @param roles
     * @return
     */
    private Integer[] roleListToArray(List<Role> roles) {
        Integer[] intRoles = new Integer[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            intRoles[i] = roles.get(i).getId();
        }
        return intRoles;
    }
}
