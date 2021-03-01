package org.labi.permissionsystem.config.mybatisPlus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: permissionsystem
 * @description: mybatisPlus配置类
 * @author: dzp
 * @create: 2021-03-01 14:03
 **/
@Configuration
public class MybatisPlusConfig {

    /**
     * 解决分页查询total和page为0的情况
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
