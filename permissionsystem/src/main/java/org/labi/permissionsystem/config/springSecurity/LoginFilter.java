package org.labi.permissionsystem.config.springSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName LoginFilter.java
 * @Description 自定义过滤器（这样就可以接收JSON格式的数据，而不是key/value格式的数据）
 * @createTime 2021年01月25日 18:27:00
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * 尝试身份验证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //不是post请求直接抛出异常
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("请求方式不支持：" + request.getMethod());
        }
        //如果是JSON传递参数，则按照JSON的方式解析，如果不是，则调用默认的方法解析
        if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                //将JSON字符串数据映射到Map上
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //得到用户名和密码
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            //将用户名和密码封装成 用户名密码验证令牌
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            //设置详细信息
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}
