package org.labi.permissionsystem.bean.beanTools;

import lombok.Data;
import org.labi.permissionsystem.bean.Role;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 用户角色工具类
 * @author: dzp
 * @create: 2021-03-03 10:34
 **/
@Data
public class UserRolesTool {
    private Integer id;

    private String name;

    private Boolean enabled;

    private String username;

    private String password;

    private List<Role> roles;
}
