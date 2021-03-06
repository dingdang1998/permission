package org.labi.permissionsystem.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName UserRoles.java
 * @Description 用户具有的角色
 * @createTime 2021年02月21日 21:41:00
 */
@Getter
@Setter
@ToString
public class UserRoles extends User implements UserDetails {
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
        return getEnabled() == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRoles userRoles = (UserRoles) o;
        return Objects.equals(getUsername(), userRoles.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}

