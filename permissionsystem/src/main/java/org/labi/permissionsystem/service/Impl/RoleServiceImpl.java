package org.labi.permissionsystem.service.Impl;

import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.dao.RoleDao;
import org.labi.permissionsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 角色ServiceImpl
 * @author: dzp
 * @create: 2021-02-22 13:35
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Integer addUserRole(int userId, Integer[] roleIds) {
        return roleDao.addUserRole(userId, roleIds);
    }

    @Override
    public void deleteUserRoleByUserId(int userId) {
        roleDao.deleteUserRoleByUserId(userId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public void addRole(Role role) {
        role.setName(addPrefix(role.getName()));
        roleDao.addRole(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserRoles(Integer userId, Integer[] rids) {
        //删除用户原有角色
        roleDao.deleteUserRoleByUserId(userId);
        //添加新的用户--角色关系
        return roleDao.addUserRole(userId, rids) == rids.length;
    }

    /**
     * 为角色name增加前缀
     *
     * @return
     */
    private String addPrefix(String name) {
        return Role.ROLE_ + name;
    }
}
