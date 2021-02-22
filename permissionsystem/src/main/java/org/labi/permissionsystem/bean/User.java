package org.labi.permissionsystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName User.java
 * @Description 用户实体类
 * @createTime 2021年01月26日 09:56:00
 */
@Data
@Accessors(chain = true)
@TableName("usr")
public class User {

    /**
     * 启用
     */
    public static final int ENABLE = 1;
    /**
     * 禁用
     */
    public static final int DISABLE = 0;

    /**
     * 用户表主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户真实姓名
     */
    @TableField(value = "name")
    private String name;
    /**
     * 用户状态
     */
    @TableField(value = "enabled")
    private Integer enabled;
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;
    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;
}
