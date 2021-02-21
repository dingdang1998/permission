package com.oarage.lsdc.commons.entity;

import java.util.Date;

/**
 * 报警的实体类.
 */
public class Alarm implements IParameter {

    private String id;
    private int tankCode;// 油罐号
    private String code;//
    private String stationCode;
    //报警状态 0：开始报警  1：结束报警    
    private int start; //start 不存数据库列，报警状态值，存于数据库addition列

    private Date startTime;
    //报警附加值 （添加数据库时，存放报警状态）
    private String addition;
    private Date insertTime;
    private Date endTime; //报警结束时间

    //冗余字段
    private Long stationId;
    private Long tankId;
    private String stationShortName;
    private String stationFullName;
    private Integer orgId;
    private String orgCode;
    private String orgName;
    private Integer pOrgId;
    private String pOrgCode;
    private String pOrgName;
    /**
     * 报警记录是否可见，陕西版本有需求
     */
    private Integer isVisible;

    /**
     * 现在用来标记是否是疑似故障：0：非疑似故障；1：疑似故障 2：未处理的 null：需求更改之前的报警数据
     */
    private Integer isRepair;

    public Integer getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Integer isRepair) {
        this.isRepair = isRepair;
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

    public void setTankCode(int tank) {
        this.tankCode = tank;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "tankCode=" + tankCode +
                ", code='" + code + '\'' +
                ", startTime=" + startTime +
                ", addition='" + addition + '\'' +
                ", endTime=" + endTime +
                ", tankId=" + tankId +
                ", start=" + start +
                ", isRepair=" + isRepair +
                ", stationCode=" + stationCode +
                ", isVisible=" + isVisible +
                '}';
    }
}
