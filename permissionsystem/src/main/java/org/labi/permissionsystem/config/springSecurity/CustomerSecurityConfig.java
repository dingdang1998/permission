package org.labi.permissionsystem.config.springSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.labi.permissionsystem.bean.User;
import org.labi.permissionsystem.service.Impl.UserServiceImpl;
import org.labi.permissionsystem.utils.CharacterBean;
import org.labi.permissionsystem.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName SecurityConfig.java
 * @Description 自定义配置类
 * @createTime 2021年01月26日 09:26:00
 */
@Configuration
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 内容类型
     */
    private static final String TYPE = "application/json;charset=utf-8";

    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Bean
    RememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("crayon", userServiceImpl);
    }

    @Bean
    SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 设置密码是否加密
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置用户相关信息
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.authenticationProvider(rememberMeAuthenticationProvider());
    }

    /**
     * 设置对静态资源不拦截
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/verifyCode");
    }

    /**
     * 验证码配置
     *
     * @return
     */
    @Bean
    Producer verifyCode() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "140");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    /**
     * 用户名和密码校验提供
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //将用户名不存在和密码错误分开
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(userServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * 自动登录的支持
     * @return
     */
    @Bean
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider("crayon");
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        //设置身份验证成功处理程序
        loginFilter.setAuthenticationSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
            //设置内容类型
            httpServletResponse.setContentType(TYPE);
            PrintWriter out = httpServletResponse.getWriter();
            //获取登陆成功的用户
            User user = (User) authentication.getPrincipal();
            //将密码隐藏
            user.setPassword("");
            RespBean success = new RespBean(CharacterBean.TWO_HUNDRED, CharacterBean.success, user);
            out.write(new ObjectMapper().writeValueAsString(success));
            out.flush();
            out.close();
        });
        //设置身份验证失败处理程序
        loginFilter.setAuthenticationFailureHandler((httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setContentType(TYPE);
            PrintWriter out = httpServletResponse.getWriter();
            String errorMsg = null;
            if (e instanceof LockedException) {
                errorMsg = "账户被锁定，请联系管理员";
            } else if (e instanceof CredentialsExpiredException) {
                errorMsg = "密码过期，请联系管理员";
            } else if (e instanceof AccountExpiredException) {
                errorMsg = "账户过期，请联系管理员";
            } else if (e instanceof DisabledException) {
                errorMsg = "账户被禁用，请联系管理员";
            } else if (e instanceof BadCredentialsException) {
                errorMsg = "密码输入错误，请重新输入";
            } else if (e instanceof UsernameNotFoundException) {
                errorMsg = e.getMessage();
            } else if (e instanceof AuthenticationServiceException) {
                errorMsg = e.getMessage();
            }
            RespBean error = new RespBean(CharacterBean.FIVE_HUNDRED, errorMsg, null);
            out.write(new ObjectMapper().writeValueAsString(error));
            out.flush();
            out.close();
        });
        //要拦截的地址
        loginFilter.setFilterProcessesUrl("/doLogin");
        //设置认证管理器
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        //只能登陆一台设备（这里只要开启并发会话控制即可，默认就是只允许登录一台设备）
        CustomerConcurrentSessionControlAuthenticationStrategy sessionStrategy = new CustomerConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        loginFilter.setRememberMeServices(rememberMeServices());
        return loginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType(TYPE);
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(RespBean.ok(CharacterBean.loginOut)));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                //没有认证时在这里结束，不要重定向
                .authenticationEntryPoint((req, resp, authException) -> {
                    resp.setContentType(TYPE);
                    resp.setStatus(401);
                    PrintWriter out = resp.getWriter();
                    if (authException instanceof InsufficientAuthenticationException) {
                        RespBean respBean = RespBean.error("登录已过期，请重新登录");
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                    }
                    out.flush();
                    out.close();
                });
        //session冲突要做出的响应
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
            HttpServletResponse resp = event.getResponse();
            resp.setContentType(TYPE);
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(RespBean.error(CharacterBean.loginAnother)));
            out.flush();
            out.close();
        }), ConcurrentSessionFilter.class);
        //自动登录过滤链
        http.addFilterAt(new RememberMeAuthenticationFilter(authenticationManagerBean(), rememberMeServices()), RememberMeAuthenticationFilter.class);
        //登录认证的过滤链
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}