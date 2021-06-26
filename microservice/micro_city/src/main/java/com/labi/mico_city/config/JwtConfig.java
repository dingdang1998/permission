package com.labi.mico_city.config;

import com.labi.mico_city.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: microservice
 * @description: jwt配置
 * @author: dzp
 * @create: 2021-06-26 08:40
 **/
@Configuration
public class JwtConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                //拦截全部
                .addPathPatterns("/**")
                //放开登录
                .excludePathPatterns("/**/login");
    }
}
