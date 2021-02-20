package org.labi.permissionsystem.verifycode;

import com.mysql.cj.util.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName VerifyCodeFilter.java
 * @Description 自定义验证码处理器
 * @createTime 2021年01月25日 19:36:00
 */
public class VerifyCodeFilter extends GenericFilterBean {
    private final String defaultFilterProcessUrl = "/doLogin";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //如果是POST请求，并且请求的地址是"/doLogin"
        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
            //前端输入的验证码
            String requestCaptcha = request.getParameter("code");
            //生成验证码时放在会话中的
            String genCaptcha = (String) request.getSession().getAttribute("index_code");
            if (StringUtils.isNullOrEmpty(requestCaptcha)) {
                throw new AuthenticationServiceException("验证码不能为空");
            }
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                throw new AuthenticationServiceException("验证码输入错误");
            }
        }
        filterChain.doFilter(request,response);
    }
}
