package com.oket.getdata.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @program: getdata
 * @description: 数据库配置信息实体类
 * @author: dzp
 * @create: 2021-05-12 09:00
 **/
@Getter
@Setter
@ToString
public class DataBaseConfigInfoEntity {
    /**
     * 油站简称
     */
    private String stationShortName;
    /**
     * ip和端口号
     */
    private String ipAndPort;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 要查询的罐号
     */
    private List<Integer> oilCanNos;

    /**
     * 校验数据完整性
     *
     * @return
     */
    public boolean checkBaseData() {
        return (this.stationShortName != null && !"".equals(this.stationShortName)) &&
                (this.ipAndPort != null && !"".equals(this.ipAndPort)) &&
                (this.password != null) &&
                (this.oilCanNos != null) &&
                (this.username != null && !"".equals(this.username));
    }
}
