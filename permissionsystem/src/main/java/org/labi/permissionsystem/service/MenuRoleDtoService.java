package org.labi.permissionsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.labi.permissionsystem.bean.MenuRoleDTO;

import java.util.List;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName MenuService.java
 * @Description 网址服务类
 * @createTime 2021年01月31日 14:36:00
 */
public interface MenuRoleDtoService extends IService<MenuRoleDTO> {
    /**
     * 获取所有路径以及他们所需要的角色
     * @return
     */
    List<MenuRoleDTO> getAllMenusWithRole();
}
