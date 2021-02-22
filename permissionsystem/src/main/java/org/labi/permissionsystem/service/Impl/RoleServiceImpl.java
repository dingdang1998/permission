package org.labi.permissionsystem.service.Impl;

import org.labi.permissionsystem.dao.RoleDao;
import org.labi.permissionsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addUserRole(int userId, int roleId) {
        roleDao.addUserRole(userId, roleId);
    }
}
