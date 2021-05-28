package com.oket.getdata.entity;

import com.oket.getdata.util.CustomStringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


/**
 * @program: getdata
 * @description: 罐存文件数据对应实体【对应中石化数据库oss_monitor_rtinventory表部分字段】
 * @author: dzp
 * @create: 2021-05-12 09:55
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 罐号
     */
    private Integer oilCanNo;
    /**
     * 油高
     */
    private Double totalHeight;
    /**
     * 油体积
     */
    private Double oilCubage;
    /**
     * 时间
     */
    private Timestamp storeTime;
    /**
     * 温度
     */
    private Double temp;

    @Override
    public String toString() {
        return oilCanNo + "," + totalHeight + "," + oilCubage + "," + CustomStringUtil.formatDate(storeTime) + "," + temp + "\n";
    }
}
