package com.oarage.lsdc.commons.entity;

import java.util.Date;

/**
 * 卸油的实体类.
 */
public class Delivery implements IParameter {

    private String id;
    private String stationCode;
    private int tankCode;// 油罐号
    private String oil;
    private Date startDate;
    private Date startTime;
    private double startVt;
    private double startV20;
    private double startOilHeight;
    private double startWaterHeight;
    private double startTemperature;
    private Date endDate;
    private Date endTime;
    private double endVt;
    private double endV20;
    private double endOilHeight;
    private double endWaterHeight;
    private double endTemperature;
    private double endEmpty;
    private double outVolume;
    private Double visualDensity;
    private Double standardDensity;
    private Double sendDensity;
    private String addition;//卸油状态   0：结束卸油     1：开始卸油
    private double deliveryVt;//卸油量
    private Date insertTime;

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
    private String orgName;
    private Integer pOrgId;
    private String pOrgCode;
    private String pOrgName;
    private String oilTypeCode;

    /**
     * 开始卸油状态
     */
    public static final String START_DELIVERY_STATUS = "1";
    /**
     * 结束卸油状态
     */
    public static final String END_DELIVERY_STATUS = "0";


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
        String tankNo = String.valueOf(tankCode);
        if (tankCode < 10) {
            tankNo = "0" + tankNo;
        }
        return tankNo;
    }

    public int getTankCode() {
        return tankCode;
    }

    public void setTankCode(int tankCode) {
        this.tankCode = tankCode;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public double getStartVt() {
        return startVt;
    }

    public void setStartVt(double startVt) {
        this.startVt = startVt;
    }

    public double getStartV20() {
        return startV20;
    }

    public void setStartV20(double startV20) {
        this.startV20 = startV20;
    }

    public double getStartOilHeight() {
        return startOilHeight;
    }

    public void setStartOilHeight(double startOilHeight) {
        this.startOilHeight = startOilHeight;
    }

    public double getStartWaterHeight() {
        return startWaterHeight;
    }

    public void setStartWaterHeight(double startWaterHeight) {
        this.startWaterHeight = startWaterHeight;
    }

    public double getStartTemperature() {
        return startTemperature;
    }

    public void setStartTemperature(double startTemperature) {
        this.startTemperature = startTemperature;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getEndVt() {
        return endVt;
    }

    public void setEndVt(double endVt) {
        this.endVt = endVt;
    }

    public double getEndV20() {
        return endV20;
    }

    public void setEndV20(double endV20) {
        this.endV20 = endV20;
    }

    public double getEndOilHeight() {
        return endOilHeight;
    }

    public void setEndOilHeight(double endOilHeight) {
        this.endOilHeight = endOilHeight;
    }

    public double getEndWaterHeight() {
        return endWaterHeight;
    }

    public void setEndWaterHeight(double endWaterHeight) {
        this.endWaterHeight = endWaterHeight;
    }

    public double getEndTemperature() {
        return endTemperature;
    }

    public void setEndTemperature(double endTemperature) {
        this.endTemperature = endTemperature;
    }

    public double getEndEmpty() {
        return endEmpty;
    }

    public void setEndEmpty(double endEmpty) {
        this.endEmpty = endEmpty;
    }

    public double getOutVolume() {
        return outVolume;
    }

    public void setOutVolume(double outVolume) {
        this.outVolume = outVolume;
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

    public Double getSendDensity() {
        return (sendDensity == null ? 0.0 : sendDensity);
    }

    public void setSendDensity(Double sendDensity) {
        this.sendDensity = (sendDensity == null ? 0.0 : sendDensity);
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
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

    /**
     * @return the stationCode
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * @param stationCode the stationCode to set
     */
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
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

    public void setDeliveryVt(double deliveryVt) {
        this.deliveryVt = deliveryVt;
    }

    public double getDeliveryVt() {
        if (addition.equals("0")) {
            return endVt - startVt + outVolume;
        }
        return deliveryVt;
    }

    public double getDeliveryV20() {
        return endV20 - startV20 + outVolume;
    }

    public void setOilShortName(String oilShortName) {
        this.oilShortName = oilShortName;
    }

    public String getOilShortName() {
        return oilShortName;
    }

    public void setOilFullName(String oilFullName) {
        this.oilFullName = oilFullName;
    }

    public String getOilFullName() {
        return oilFullName;
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

    public void setOilTypeCode(String oilTypeCode) {
        this.oilTypeCode = oilTypeCode;
    }

    public String getOilTypeCode() {
        return oilTypeCode;
    }

}
