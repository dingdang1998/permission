package org.labi.testredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.oarage.lsdc.commons.entity")
@ComponentScan("org.labi.testredis.Controller")
public class TestredisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestredisApplication.class, args);
    }

}
