package org.labi.permissionsystem.config;

import org.labi.permissionsystem.bean.MenuRoleDTO;
import org.labi.permissionsystem.service.MenuRoleDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName CustomFilterInvocationSecurityMetadataSource.java
 * @Description 根据用户传来的地址, 分析出请求需要的角色
 * @createTime 2021年01月27日 19:43:00
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuRoleDtoService menuRoleDtoService;
    /**
     * 蚂蚁路径匹配器
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //记录该请求路径所需要的角色
        List<String> roles = new ArrayList<>();
        //获取请求路径
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //获取所有路径
        List<MenuRoleDTO> allMenusWithRole = menuRoleDtoService.getAllMenusWithRole();
        for (MenuRoleDTO menuRoleDTO : allMenusWithRole) {
            if (antPathMatcher.match(menuRoleDTO.getUrl(), requestUrl)) {
                roles.add(menuRoleDTO.getRname());
            }
            String[] str = roles.toArray(new String[roles.size()]);
            return SecurityConfig.createList(str);
        }
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
