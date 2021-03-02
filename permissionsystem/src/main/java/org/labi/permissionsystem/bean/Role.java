package org.labi.permissionsystem.bean;

import lombok.Data;

/**
 * @program: permissionsystem
 * @description: 角色实体
 * @author: dzp
 * @create: 2021-02-21 14:04
 **/
@Data
public class Role {

    /**
     * name前缀
     */
    public static final String ROLE_ = "ROLE_";
    /**
     * 角色表主键id
     */
    private Integer id;
    /**
     * 角色
     */
    private String name;
    /**
     * 角色中文含义
     */
    private String nameZh;
}
