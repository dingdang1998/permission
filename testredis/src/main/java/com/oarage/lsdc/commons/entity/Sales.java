package com.oarage.lsdc.commons.entity;

import java.util.Date;

/**
 * @program: testredis
 * @description: 实体类
 * @author: dzp
 * @create: 2021-01-06 10:11
 **/
public class Sales {
    private String id;
    private Date startTime;//开始加油时间
    private Date endTime;//结束加油时间
    private double volume;//体积
    private double allVolume;//总累
    private String hoseCode;//油枪编号
    private String stationCode;//加油站编码
    private Long importLogId;
    private String serialNumber;//序列号
    private Date insertTime;//数据插入时间
    private Integer fyType;
    private Date ceSysTime;
    private Date seSysTime;
    private String addition;//附加信息
    private String isStart;//开始或结束
    private String unit;//单位
    private double price;//单价
    private double amount;//金额
    private String machineCode;
    private String tankCode;
    private String oilCode;
    private String stationName;
    private String oilName;
    private Integer orgId;
    private String orgCode;
    private String pOrgName;
    private String orgName;
    private Integer pOrgId;
    private String pOrgCode;
    //是否是按照分钟统计数据，用于分表生成命名
    private boolean isMinute;
    private Long stationId;
    private Long hoseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getAllVolume() {
        return allVolume;
    }

    public void setAllVolume(double allVolume) {
        this.allVolume = allVolume;
    }

    public String getHoseCode() {
        return hoseCode;
    }

    public void setHoseCode(String hoseCode) {
        this.hoseCode = hoseCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Long getImportLogId() {
        return importLogId;
    }

    public void setImportLogId(Long importLogId) {
        this.importLogId = importLogId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getFyType() {
        return fyType;
    }

    public void setFyType(Integer fyType) {
        this.fyType = fyType;
    }

    public Date getCeSysTime() {
        return ceSysTime;
    }

    public void setCeSysTime(Date ceSysTime) {
        this.ceSysTime = ceSysTime;
    }

    public Date getSeSysTime() {
        return seSysTime;
    }

    public void setSeSysTime(Date seSysTime) {
        this.seSysTime = seSysTime;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getTankCode() {
        return tankCode;
    }

    public void setTankCode(String tankCode) {
        this.tankCode = tankCode;
    }

    public String getOilCode() {
        return oilCode;
    }

    public void setOilCode(String oilCode) {
        this.oilCode = oilCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getpOrgName() {
        return pOrgName;
    }

    public void setpOrgName(String pOrgName) {
        this.pOrgName = pOrgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getpOrgId() {
        return pOrgId;
    }

    public void setpOrgId(Integer pOrgId) {
        this.pOrgId = pOrgId;
    }

    public String getpOrgCode() {
        return pOrgCode;
    }

    public void setpOrgCode(String pOrgCode) {
        this.pOrgCode = pOrgCode;
    }

    public boolean isMinute() {
        return isMinute;
    }

    public void setMinute(boolean minute) {
        isMinute = minute;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getHoseId() {
        return hoseId;
    }

    public void setHoseId(Long hoseId) {
        this.hoseId = hoseId;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id='" + id + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", volume=" + volume +
                ", allVolume=" + allVolume +
                ", hoseCode='" + hoseCode + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", importLogId=" + importLogId +
                ", serialNumber='" + serialNumber + '\'' +
                ", insertTime=" + insertTime +
                ", fyType=" + fyType +
                ", ceSysTime=" + ceSysTime +
                ", seSysTime=" + seSysTime +
                ", addition='" + addition + '\'' +
                ", isStart='" + isStart + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", machineCode='" + machineCode + '\'' +
                ", tankCode='" + tankCode + '\'' +
                ", oilCode='" + oilCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", oilName='" + oilName + '\'' +
                ", orgId=" + orgId +
                ", orgCode='" + orgCode + '\'' +
                ", pOrgName='" + pOrgName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", pOrgId=" + pOrgId +
                ", pOrgCode='" + pOrgCode + '\'' +
                ", isMinute=" + isMinute +
                ", stationId=" + stationId +
                ", hoseId=" + hoseId +
                '}';
    }
}
