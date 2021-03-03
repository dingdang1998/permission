package org.labi.permissionsystem.utils;

import org.labi.permissionsystem.bean.UserRoles;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @program: permissionsystem
 * @description: 用户角色工具类
 * @author: dzp
 * @create: 2021-03-03 09:21
 **/
public class UserRolesUtils {

    /**
     * 获取当前用户
     * 当服务端重启以后，前端没有重新登录，会抛出ClassCastException异常
     * 将此异常捕获，重新抛出异常，交由SpringSecurity拦截
     *
     * @return
     */
    public static UserRoles getCurrent() {
        try {
            return (UserRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            throw new InsufficientAuthenticationException("");
        }
    }
}
