package org.labi.permissionsystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: permissionsystem
 * @description: 角色实体
 * @author: dzp
 * @create: 2021-02-21 14:04
 **/
@Data
@Accessors(chain = true)
@TableName("role")
public class Role {
    /**
     * 角色表主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色
     */
    @TableField(value = "name")
    private String name;
    /**
     * 角色中文含义
     */
    @TableField(value = "nameZh")
    private String nameZh;
}
