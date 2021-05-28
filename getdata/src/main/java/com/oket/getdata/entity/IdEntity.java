package com.oket.getdata.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: getdata
 * @description: 记录取数的id
 * @author: dzp
 * @create: 2021-05-13 14:22
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdEntity {
    /**
     * 【oss_monitor_rtinventory表】即罐存数据--上一次取数的id
     */
    private Integer inventoryId;
    /**
     * 【rt_vouch表】即销售数据--上一次取数的id
     */
    private Integer houseOutId;

    @Override
    public String toString() {
        return (inventoryId == null ? "null" : inventoryId)
                + "," +
                (houseOutId == null ? "null" : houseOutId);
    }
}
