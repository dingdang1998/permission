package org.labi.permissionsystem.config.springSecurity;

import org.labi.permissionsystem.bean.Menu;
import org.labi.permissionsystem.bean.Role;
import org.labi.permissionsystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName CustomFilterInvocationSecurityMetadataSource.java
 * @Description 根据用户传来的地址, 分析出该地址需要的角色
 * @createTime 2021年01月27日 19:43:00
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    /**
     * 注册用户的路径
     */
    private static final String addUser = "/user/addUser*";

    /**
     * 检查用户名路径
     */
    private static final String checkUsername = "/user/checkUsername*";

    /**
     * 路径匹配器
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求路径
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        if (antPathMatcher.match(addUser, requestUrl) || antPathMatcher.match(checkUsername, requestUrl)) {
            //放开一些不需要验证的路径
            return SecurityConfig.createList("ROLE_PERMIT");
        } else {
            //获取所有路径及其所匹配的角色
            List<Menu> allMenusWithRole = menuService.getAllMenusWithRole();
            for (Menu menu : allMenusWithRole) {
                if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                    List<Role> roles = menu.getRoles();
                    String[] roleNames = new String[roles.size()];
                    for (int i = 0; i < roles.size(); i++) {
                        roleNames[i] = roles.get(i).getName();
                    }
                    return SecurityConfig.createList(roleNames);
                }
            }
            //匹配不到的路径
            return SecurityConfig.createList("ROLE_LOGIN");
        }

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
