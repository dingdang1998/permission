package com.labi.micro_city2.feign;

import com.labi.entity.Message;
import com.labi.entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @program: microservice
 * @description:
 * @author: dzp
 * @create: 2021-06-22 18:26
 **/
@Component
public class CityClientImpl implements CityClient{
    @Override
    public Message add() {
        return new Message(false, StatusCode.ERROR,"请求失败，触发本地熔断");
    }
}
