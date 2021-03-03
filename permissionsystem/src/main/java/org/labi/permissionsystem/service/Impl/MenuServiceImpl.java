package org.labi.permissionsystem.service.Impl;

import org.labi.permissionsystem.bean.Menu;
import org.labi.permissionsystem.dao.MenuDao;
import org.labi.permissionsystem.service.MenuService;
import org.labi.permissionsystem.utils.UserRolesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Menu> getMenusByUserId() {
        //获取当前登陆用户id
        return menuDao.getMenusByUserId(UserRolesUtils.getCurrent().getId());
    }

    @Override
    public List<Integer> getMenuIdsByRid(Integer rid) {
        return menuDao.getMenuIdsByRid(rid);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuDao.getAllMenus();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMidsByRid(Integer rid, Integer[] mids) {
        //删除角色--菜单关系
        menuDao.deleteByRid(rid);
        //新增关系
        if (mids == null || mids.length == 0) {
            return true;
        }
        return menuDao.updateMidsByRid(rid, mids) == mids.length;
    }
}
