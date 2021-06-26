package com.labi.mico_city.interceptor;

import com.labi.entity.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: microservice
 * @description: jwt拦截器
 * @author: dzp
 * @create: 2021-06-25 17:45
 **/
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("jwt拦截器...");
        String authentication = request.getHeader("authentication");
        if (authentication != null && authentication.startsWith("labi-")) {
            String token = authentication.substring(5);
            Claims claims = jwtUtil.parseJwt(token);
            String roles = (String) claims.get("roles");
            if ("admin".equals(roles)) {
                request.setAttribute("claims", claims);
            }
        }
        return true;
    }
}
