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

    private static final String POST = "POST";

    /**
     * 1、拦截登录请求，获取username和password
     * 2、构建UsernamePasswordAuthenticationToken对象，username--principal  password--credentials
     * 3、为details属性赋值
     * 4、调用authenticate方法做校验
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //不是post请求直接抛出异常
        if (!POST.equals(request.getMethod())) {
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
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            //如果是key--value传值，则调用父类的处理方法
            return super.attemptAuthentication(request, response);
        }
    }
}
