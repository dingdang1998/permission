package org.labi.permissionsystem.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.labi.permissionsystem.bean.MenuRoleDTO;
import org.labi.permissionsystem.dao.MenuRoleDtoDao;
import org.labi.permissionsystem.service.MenuRoleDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName MenuRoleDtoServiceImpl.java
 * @Description TODO
 * @createTime 2021年01月31日 15:13:00
 */
@Service
public class MenuRoleDtoServiceImpl extends ServiceImpl<MenuRoleDtoDao, MenuRoleDTO> implements MenuRoleDtoService {
    @Override
    public List<MenuRoleDTO> getAllMenusWithRole() {
        return list();
    }
}
