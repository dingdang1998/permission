package org.labi.permissionsystem.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName MenuRoleDTO.java
 * @Description 菜单表和角色表关联视图实体类
 * @createTime 2021年01月31日 15:04:00
 */
@Data
@Accessors(chain = true)
@TableName("v_menu_role")
public class MenuRoleDTO {
    /**
     * 菜单表主键id
     */
    @TableField(value = "id")
    private Integer id;
    /**
     * 路径匹配规则
     */
    @TableField(value = "url")
    private String url;
    /**
     * 路径
     */
    @TableField(value = "path")
    private String path;
    /**
     * 状态
     */
    @TableField(value = "enabled")
    private Integer enabled;
    /**
     * 角色表主键
     */
    @TableField(value = "rid")
    private Integer rid;
    /**
     * 角色
     */
    @TableField(value = "rname")
    private String rname;
    /**
     * 角色中文名称
     */
    @TableField(value = "rnameZh")
    private String rnameZh;
}
