package org.labi.permissionsystem.config.springSecurity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: permissionsystem
 * @description:
 * @author: dzp
 * @create: 2021-03-05 17:25
 **/
public class testf extends RememberMeAuthenticationFilter {
    public testf(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices) {
        super(authenticationManager, rememberMeServices);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("1111111111111111111111");
        super.doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
}
