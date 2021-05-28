package com.oket.getdata.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: getdata
 * @description: 单站枪罐关系对应实体【对应中石化数据库sm_oilgun表部分字段】
 * @author: dzp
 * @create: 2021-05-12 11:35
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MappingEntity {
    /**
     * 油枪编号
     */
    private Integer oilGunNo;
    /**
     * 油罐编号
     */
    private Integer oilCanNo;

    @Override
    public String toString() {
        return oilGunNo + " " + oilCanNo + "\n";
    }
}
