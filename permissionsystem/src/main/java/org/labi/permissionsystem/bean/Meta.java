package org.labi.permissionsystem.bean;

import lombok.Data;

/**
 * @program: permissionsystem
 * @description: 元数据
 * @author: dzp
 * @create: 2021-02-22 15:28
 **/
@Data
public class Meta {
    private Boolean keepAlive;
    private Boolean requireAuth;
}
