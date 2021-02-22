package org.labi.permissionsystem.service.Impl;

import org.labi.permissionsystem.bean.Menu;
import org.labi.permissionsystem.dao.MenuDao;
import org.labi.permissionsystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 菜单ServiceImpl
 * @author: dzp
 * @create: 2021-02-22 15:34
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuDao.getAllMenusWithRole();
    }
}
