package com.oket.getdata.entity;

import com.oket.getdata.util.CustomStringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @program: getdata
 * @description: 销售文件数据对应实体【对应中石化数据库rt_vouch表部分字段】
 * @author: dzp
 * @create: 2021-05-12 10:56
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoseOutEntity {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 加油量
     */
    private Double amount;
    /**
     * 枪号
     */
    private Integer oilGunNo;
    /**
     * 加油开始时间
     */
    private Timestamp takeDate;
    /**
     * 加油结束时间
     */
    private Timestamp getTime;
    /**
     * 泵码数
     */
    private Double pumPno;

    @Override
    public String toString() {
        return amount + "," + oilGunNo + "," + CustomStringUtil.formatDate(takeDate) + "," + CustomStringUtil.formatDate(getTime) + "," + pumPno + "\n";
    }
}
