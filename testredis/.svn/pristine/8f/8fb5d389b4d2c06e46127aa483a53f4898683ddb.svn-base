package com.oarage.lsdc.commons.entity;

import java.util.Date;

/**
 * 罐存的实体类.
 */
public class Inventory implements IParameter {

    private String id;
    private String StationCode;
    private int tank;// 罐号
    private String oil;// 油品号
    private double vt;// 体积
    private double v20;
    private double empty;
    private double oilHeight;
    private double waterHeight;// 水高
    private double temperature;
    private double waterVolume;
    private Double visualDensity;
    private Double standardDensity;
    private String addition;
    private Date time;
    private String tankStatus;//油罐状态

    private Date insertTime;
    private int CLASS_TIME_START = 0;
    private int CLASS_NO_START = 8;
    private int CLASS_LENGTH = 10;

    private Long stationId;
    private Long tankId;
    private Long oilId;
    private String oilShortName;
    private String oilFullName;
    private Integer oilTypeId;
    private String oilTypeShortName;
    private String oilTypeFullName;
    private String stationShortName;
    private String stationFullName;
    private Integer orgId;
    private String orgCode;
    private String pOrgName;
    private String orgName;
    private Integer pOrgId;
    private String pOrgCode;
    private String oilTypeCode;


    public String getStationShortName() {
        return stationShortName;
    }

    public void setStationShortName(String stationShortName) {
        this.stationShortName = stationShortName;
    }

    public String getStationFullName() {
        return stationFullName;
    }

    public void setStationFullName(String stationFullName) {
        this.stationFullName = stationFullName;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getpOrgName() {
        return pOrgName;
    }

    public void setpOrgName(String pOrgName) {
        this.pOrgName = pOrgName;
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

    /**
     * @return the insertTime
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime the insertTime to set
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * 拼装油罐编号成两位
     *
     * @return String
     */
    private String getTankNo() {
        String tankNo = String.valueOf(tank);
        if (tank < 10) {
            tankNo = "0" + tankNo;
        }
        return tankNo;
    }

    public String getStationCode() {
        return StationCode;
    }

    public void setStationCode(String stationCode) {
        StationCode = stationCode;
    }

    public String getTankStatus() {
        return tankStatus;
    }

    public void setTankStatus(String tankStatus) {
        this.tankStatus = tankStatus;
    }

    public int getTank() {
        return tank;
    }

    public void setTank(int tank) {
        this.tank = tank;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public double getVt() {
        return vt;
    }

    public void setVt(double vt) {
        this.vt = vt;
    }

    public double getV20() {
        return v20;
    }

    public void setV20(double v20) {
        String v20Str = String.valueOf(v20);
        if (v20Str.length() > 10) {
            v20 = 0;
        }
        int index = v20Str.indexOf(".");
        if (index != -1) {
            String decimal = v20Str.substring(index + 1);
            if (decimal.length() > 1) {
                v20 = 0;
            }
        }
        this.v20 = v20;
    }

    public double getEmpty() {
        return empty;
    }

    public void setEmpty(double empty) {
        this.empty = empty;
    }

    public double getOilHeight() {
        return oilHeight;
    }

    public void setOilHeight(double oilHeight) {
        this.oilHeight = oilHeight;
    }

    public double getWaterHeight() {
        return waterHeight;
    }

    public void setWaterHeight(double waterHeight) {
        this.waterHeight = waterHeight;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if (temperature > 100) {
            temperature = 0.0;
        }
        this.temperature = temperature;
    }

    public double getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(double waterVolume) {
        this.waterVolume = waterVolume;
    }

    public Double getVisualDensity() {
        return (visualDensity == null ? 0.0 : visualDensity);
    }

    public void setVisualDensity(Double visualDensity) {
        this.visualDensity = (visualDensity == null ? 0.0 : visualDensity);
    }

    public Double getStandardDensity() {
        return (standardDensity == null ? 0.0 : standardDensity);
    }

    public void setStandardDensity(Double standardDensity) {
        this.standardDensity = (standardDensity == null ? 0.0 : standardDensity);
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String pack() {
        // TODO 暂不实现
        return null;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getTankId() {
        return tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
    }

    public Long getOilId() {
        return oilId;
    }

    public void setOilId(Long oilId) {
        this.oilId = oilId;
    }

    public void setOilShortName(String oilShortName) {
        this.oilShortName = oilShortName;
    }

    public String getOilShortName() {
        return oilShortName;
    }

    public void setOilTypeId(Integer oilTypeId) {
        this.oilTypeId = oilTypeId;
    }

    public Integer getOilTypeId() {
        return oilTypeId;
    }

    public void setOilTypeShortName(String oilTypeShortName) {
        this.oilTypeShortName = oilTypeShortName;
    }

    public String getOilTypeShortName() {
        return oilTypeShortName;
    }

    public void setOilTypeFullName(String oilTypeFullName) {
        this.oilTypeFullName = oilTypeFullName;
    }

    public String getOilTypeFullName() {
        return oilTypeFullName;
    }

    public void setOilFullName(String oilFullName) {
        this.oilFullName = oilFullName;
    }

    public String getOilFullName() {
        return oilFullName;
    }

    public void setOilTypeCode(String oilTypeCode) {
        this.oilTypeCode = oilTypeCode;
    }

    public String getOilTypeCode() {
        return oilTypeCode;
    }


    @Override
    public String toString() {
        return "Inventory{" +
                "tank=" + tank +
                ", oil='" + oil + '\'' +
                ", tankId=" + tankId +
                ", oilId=" + oilId +
                ", oilShortName='" + oilShortName + '\'' +
                ", oilFullName='" + oilFullName + '\'' +
                ", oilTypeId=" + oilTypeId +
                ", oilTypeShortName='" + oilTypeShortName + '\'' +
                ", oilTypeFullName='" + oilTypeFullName + '\'' +
                '}';
    }
}
