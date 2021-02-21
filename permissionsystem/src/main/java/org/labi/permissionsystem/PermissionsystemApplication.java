package org.labi.permissionsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.labi.permissionsystem.dao")
@ComponentScan("org.labi.permissionsystem.dao")
@ComponentScan("org.labi.permissionsystem")
public class PermissionsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionsystemApplication.class, args);
    }

}
