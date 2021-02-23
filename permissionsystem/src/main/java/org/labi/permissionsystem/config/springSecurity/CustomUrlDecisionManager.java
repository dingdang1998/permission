package org.labi.permissionsystem.config.springSecurity;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName CustomUrlDecisionManager.java
 * @Description 自定义网址决策管理器
 * @createTime 2021年01月27日 19:22:00
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {

    /**
     * 这里涉及到一个all和any的问题：假设当前用户具备角色A、角色B，
     * 当前请求需要角色B、角色C，那么是要当前用户要包含所有请求角色才算授权成功还是只要包含一个就算授权成功？
     * 这里采用了第二种方案，即只要包含一个即可
     *
     * @param authentication 当前登录用户所具有的角色信息
     * @param o
     * @param collection     当前请求需要的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute();
            //拥有permit的接口不需要验证
            if ("ROLE_permit".equals(needRole)) {
                return;
            }

            if ("ROLE_LOGIN".equals(needRole)) {
                //认证方式属于匿名认证令牌
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登陆，请登录");
                }
            } else {
                return;
            }

            //当前登录用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals(needRole)) {
                    return;
                } else {
                    throw new AccessDeniedException("权限不足，请联系管理员");
                }
            }
        }

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
