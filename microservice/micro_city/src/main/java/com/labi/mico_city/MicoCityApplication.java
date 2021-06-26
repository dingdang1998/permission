package com.labi.mico_city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("com.labi.mico_city.mapper")
@SpringBootApplication
@EnableEurekaClient
public class MicoCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicoCityApplication.class, args);
    }

}
