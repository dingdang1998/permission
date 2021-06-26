package com.labi.mico_city.config;

import com.labi.entity.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-25 15:20
 **/
@Configuration
public class CustomConfig {

    @Bean
    public JwtUtil getJwtUtil() {
        return new JwtUtil();
    }
}
