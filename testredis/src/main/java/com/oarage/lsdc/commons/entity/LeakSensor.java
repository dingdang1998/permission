package com.oarage.lsdc.commons.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * @author wanghaoyu
 * @date 2018/11/8 - 15:38
 */
public class LeakSensor implements IParameter, Serializable {
    private static final long serialVersionUID = 1L;
    //FF双层罐传感器
    public static final int TYPE_FF = 1;
    //SF双层罐传感器
    public static final int TYPE_SF = 2;
    //双层管线测漏传感器
    public static final int TYPE_PIPELINE = 3;
    //可燃气体测漏传感器
    public static final int COMBUSTIBLE_GAS = 4;

    private String id;
    private int leakSensorType;//测漏传感器类型
    private int tankCode;//油罐编号（或加油机编号） 当为加油机底盘测漏传感器时，此数据为加油机	编号，其余传感器类型情况下为油罐编号
    private int sensorNo;//传感器编号
    private int leakMethod;//测漏方式
    private int sensorStatus;//传感器状态
    private String alarmCode;//报警代码 可能有多个，用 "," 逗号隔开
    private Double nowValue;//当前数值
    private Double alarmLimitValue;//报警门限值
    private Double warnLimitValue;//预警门限值
    private Date receiveDate;//接收日期
    private String stationCode;//油站编号

    private Long stationId;

    private String stationShortName;
    private String stationFullName;
    private Integer orgId;
    private String orgCode;
    private String orgName;
    private Integer pOrgId;
    private String pOrgCode;
    private String pOrgName;
    private String lonlat;//经度，纬度
    /**
     * 报警开始时间
     */
    private Date startTime;
    /**
     * 报警结束时间
     */
    private Date endTime;

    public Integer getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Integer isRepair) {
        this.isRepair = isRepair;
    }

    /**
     * 现在用来标记是否是疑似故障：0：非疑似故障；1：疑似故障 2：未处理的 null：需求更改之前的报警数据
     */
    private Integer isRepair;

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

    public String getLonlat() {
        return lonlat;
    }

    public void setLonlat(String lonlat) {
        this.lonlat = lonlat;
    }

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

    public String getpOrgName() {
        return pOrgName;
    }

    public void setpOrgName(String pOrgName) {
        this.pOrgName = pOrgName;
    }


    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getStationId() {
        return stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLeakSensorType() {
        return leakSensorType;
    }

    public void setLeakSensorType(int leakSensorType) {
        this.leakSensorType = leakSensorType;
    }

    public int getTankCode() {
        return tankCode;
    }

    public void setTankCode(int tankCode) {
        this.tankCode = tankCode;
    }

    public int getSensorNo() {
        return sensorNo;
    }

    public void setSensorNo(int sensorNo) {
        this.sensorNo = sensorNo;
    }

    public int getLeakMethod() {
        return leakMethod;
    }

    public void setLeakMethod(int leakMethod) {
        this.leakMethod = leakMethod;
    }

    public int getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(int sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public Double getNowValue() {
        return nowValue;
    }

    public void setNowValue(Double nowValue) {
        this.nowValue = nowValue;
    }

    public Double getAlarmLimitValue() {
        return alarmLimitValue;
    }

    public void setAlarmLimitValue(Double alarmLimitValue) {
        this.alarmLimitValue = alarmLimitValue;
    }

    public Double getWarnLimitValue() {
        return warnLimitValue;
    }

    public void setWarnLimitValue(Double warnLimitValue) {
        this.warnLimitValue = warnLimitValue;
    }

    @Override
    public String pack() {
        // TODO 暂不实现
        return null;
    }
}
