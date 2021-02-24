package org.labi.permissionsystem.service.Impl;

import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.dao.RoleDao;
import org.labi.permissionsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addUserRole(int userId, Integer[] roleIds) {
        roleDao.addUserRole(userId, roleIds);
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
        roleDao.addRole(role);
    }
}
