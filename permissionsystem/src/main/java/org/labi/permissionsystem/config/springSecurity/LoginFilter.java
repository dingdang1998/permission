package org.labi.permissionsystem.config.springSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.labi.permissionsystem.bean.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistryImpl;
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

    @Autowired
    private SessionRegistryImpl sessionRegistryImpl;

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
        //获取服务端生成的验证码
        String verifyCode = (String) request.getSession().getAttribute("verify_code");
        //如果是JSON传递参数，则按照JSON的方式解析，如果不是，则调用默认的方法解析
        if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                //将JSON字符串数据映射到Map上
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                String code = loginData.get("code");
                checkCode(code, verifyCode);
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

            //手动注册session
            UserRoles principal = new UserRoles();
            principal.setUsername(username);
            sessionRegistryImpl.registerNewSession(request.getSession(true).getId(), principal);

            //获取是否需要自动登录
            String remember = loginData.get("remember-me");
            if (remember != null) {
                request.setAttribute("remember-me", "true");
            }
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            //如果是key--value传值，则调用父类的处理方法
            checkCode(request.getParameter("code"), verifyCode);
            return super.attemptAuthentication(request, response);
        }
    }

    /**
     * 检查验证码是否正确
     *
     * @param code
     * @param verifyCode
     */
    public void checkCode(String code, String verifyCode) {
        if (code == null || "".equals(code) || !code.equals(verifyCode)) {
            //验证码不正确
            throw new AuthenticationServiceException("验证码错误");
        }
    }
}
