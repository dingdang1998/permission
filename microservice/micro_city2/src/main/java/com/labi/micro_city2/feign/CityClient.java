package com.labi.micro_city2.feign;

import com.labi.entity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: microservice
 * @description: 调用city的远程对象
 * @author: dzp
 * @create: 2021-06-16 19:21
 **/
@FeignClient(value = "city", fallback = CityClientImpl.class)
public interface CityClient {
    @GetMapping("add")
    Message add();
}
