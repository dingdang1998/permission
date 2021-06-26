package com.labi.micro_city2.mapper;


import com.labi.micro_city2.entity.City;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 */
@Repository
public interface CityMapper {
    /**
     * mybatis： 1.注解   2.SQL映射文件
     *
     * @param city
     * @return
     */
    @Insert("insert into city(id,name,area) values(#{id},#{name},#{area})")
    public boolean addCity(City city);

    @Delete("delete from city where id = #{id}")
    public boolean deleteById(Integer id);

    @Update("update city set name = #{name} ,area=#{area} where id = #{id}")
    public boolean updateCityById(City city);

    @Select("select * from city")
    public List<City> queryCities();

}
