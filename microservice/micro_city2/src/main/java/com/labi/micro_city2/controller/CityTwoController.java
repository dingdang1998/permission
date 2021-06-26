package com.labi.micro_city2.controller;

import com.labi.entity.Message;
import com.labi.entity.StatusCode;
import com.labi.micro_city2.entity.City;
import com.labi.micro_city2.feign.CityClient;
import com.labi.micro_city2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author admin
 */
@RestController
public class CityTwoController {
    /**
     * 调用远程的service
     */
    @Autowired
    private CityClient cityClient;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用feign实现远程调用
     *
     * @return
     */
    @GetMapping("add_city")
    public Message add() {
        return cityClient.add();
    }

    /**
     * 使用ribbon实现远程调用
     */
    @GetMapping("/findCityByIdRibbon/add")
    public Message findCityByIdRibbon() {
        System.out.println("ribbon调用远程方法");
        //返回值 = getForObject( 映射地址，返回值类型，参数)
        return restTemplate.getForObject("http://city/add", Message.class);//方法的返回值，就是远程访问方法的返回值
    }

    /**
     * 调用自己的service
     **/
    @Autowired
    private CityService cityService;

    @PostMapping(value = "addCity2")
    public Message addCity(@RequestBody City city) {
        boolean result = cityService.addCity(city);
        return new Message(true, StatusCode.OK, result);
    }

    @DeleteMapping("deleteById2/{id}")
    public Message deleteById(@PathVariable Integer id) {
        boolean result = cityService.deleteById(id);
        return new Message(true, StatusCode.OK, result);
    }

    @PutMapping("updateCityById2")
    public Message updateCityById(@RequestBody City city) {
        boolean result = cityService.updateCityById(city);
        return new Message(true, StatusCode.OK, result);
    }

    @GetMapping("queryCities2")
    public Message queryCities() {
        List<City> cities = cityService.queryCities();
        return new Message(true, StatusCode.OK, cities);
    }
}
