package com.labi.micro_city2.configbean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-21 16:41
 **/
@Configuration
public class ConfigBean {

    /**
     * 将远程访问对象放入IOC容器
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
