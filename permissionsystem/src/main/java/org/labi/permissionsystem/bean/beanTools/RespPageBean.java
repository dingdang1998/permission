package org.labi.permissionsystem.bean.beanTools;

import lombok.Data;

import java.util.List;

/**
 * @program: permissionsystem
 * @description: 分页查询返回实体
 * @author: dzp
 * @create: 2021-02-23 10:30
 **/
@Data
public class RespPageBean {
    /**
     * 查询总数
     */
    private Long total;

    /**
     * 查询内容
     */
    private List<?> data;
}
