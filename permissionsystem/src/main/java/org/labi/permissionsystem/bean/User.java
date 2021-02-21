package org.labi.permissionsystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class User implements UserDetails {

    public static final int ENABLE = 0;
    public static final int DISABLE = 1;

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

    /**
     * 用户具有的角色
     */
    private List<Role> roles;


    /**
     * 用户所具有的角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否被禁用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        if (enabled == 1) {
            return true;
        }
        return false;
    }
}
