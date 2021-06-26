package com.labi.mico_city.controller;

import com.labi.entity.Message;
import com.labi.entity.StatusCode;
import com.labi.mico_city.entity.City;
import com.labi.mico_city.service.CityService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author admin
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping(value = "addCity")
    public Message addCity(@RequestBody City city) {
        boolean result = cityService.addCity(city);
        return new Message(true, StatusCode.OK, result);
    }

    @DeleteMapping("deleteById/{id}")
    public Message deleteById(@PathVariable Integer id) {
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims == null) {
            return new Message(false, StatusCode.ERROR, "权限不足");
        }
        return new Message(true, StatusCode.OK, "删除成功");
    }

    @PutMapping("updateCityById")
    public Message updateCityById(@RequestBody City city) {
        boolean result = cityService.updateCityById(city);
        return new Message(true, StatusCode.OK, result);
    }

    @GetMapping("queryCities")
    public Message queryCities() {
        List<City> cities = cityService.queryCities();
        return new Message(true, StatusCode.OK, cities);
    }

    @GetMapping("add")
    public Message add() {
        String add = cityService.add();
        return new Message(true, StatusCode.OK, add);
    }
}
