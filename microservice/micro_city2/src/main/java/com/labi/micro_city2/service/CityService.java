package com.labi.micro_city2.service;

import com.labi.micro_city2.entity.City;
import com.labi.micro_city2.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public boolean addCity(City city) {
        return cityMapper.addCity(city);
    }

    public boolean deleteById(Integer id) {
        return cityMapper.deleteById(id);
    }

    public boolean updateCityById(City city) {
        return cityMapper.updateCityById(city);
    }

    public List<City> queryCities() {
        return cityMapper.queryCities();
    }
}
