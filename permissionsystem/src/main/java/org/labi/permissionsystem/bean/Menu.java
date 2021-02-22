package org.labi.permissionsystem.bean;

import lombok.Data;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 菜单实体类
 * @author: dzp
 * @create: 2021-02-22 15:18
 **/
@Data
public class Menu {
    private Integer id;
    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Meta meta;

    private Integer parentId;

    private Boolean enabled;
    private List<Menu> children;
    private List<Role> roles;
}
