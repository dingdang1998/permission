package com.labi.micro_configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-24 18:54
 **/
@EnableConfigServer
@SpringBootApplication
public class MicroConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroConfigApplication.class, args);
    }
}
