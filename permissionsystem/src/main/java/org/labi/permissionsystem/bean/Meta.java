package org.labi.permissionsystem.bean;

import lombok.Data;

/**
 * @program: permissionsystem
 * @description: 菜单中元数据
 * @author: dzp
 * @create: 2021-02-22 15:28
 **/
@Data
public class Meta {
    /**
     * 是否使用
     */
    private Boolean keepAlive;
    /**
     * 是否需要身份验证
     */
    private Boolean requireAuth;
}
