package com.oarage.lsdc.commons.entity;

import java.util.Date;

/**
 * 测漏结果数据信息
 * @author changwei
 *
 */
public class LeakResult implements IParameter {

	private Long id;
	private String stationCode;//油站号
	private Integer tank;//罐号
	private String oil;//油品号
	private Double leakCriteria;//测漏标准
	private String volumeExpansivity;//体积膨胀系数
	private Double leakRate;//测漏率
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private Double initOilHigh;//初始油高
	private Double initVolume;//初始体积
	private Double initCriteriaVolume;//初始标准体积
	private Double initTemperature;//初始温度
	private Double initWaterHigh;//初始水高
	private Double initWaterValume;//初始水体积
	private Double endOilHigh;//结束油高
	private Double endVolume;//结束体积
	private Double endCriteriaVolume;//结束标准体积
	private Double endTemperature;//结束温度
	private Double endWaterHigh;//结束水高
	private Double endWaterValume;//结束水体积
	private Date insertTime;//插入时间
	private String planNo;//测漏计划号
	private Integer leakResult;//测漏结果
	
	private Long stationId;
	private Long tankId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public Integer getTank() {
		return tank;
	}
	public void setTank(Integer tank) {
		this.tank = tank;
	}
	public String getOil() {
		return oil;
	}
	public void setOil(String oil) {
		this.oil = oil;
	}
	public Double getLeakCriteria() {
		return leakCriteria;
	}
	public void setLeakCriteria(Double leakCriteria) {
		this.leakCriteria = leakCriteria;
	}
	public String getVolumeExpansivity() {
		return volumeExpansivity;
	}
	public void setVolumeExpansivity(String volumeExpansivity) {
		this.volumeExpansivity = volumeExpansivity;
	}
	public Double getLeakRate() {
		return leakRate;
	}
	public void setLeakRate(Double leakRate) {
		this.leakRate = leakRate;
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
	public Double getInitOilHigh() {
		return initOilHigh;
	}
	public void setInitOilHigh(Double initOilHigh) {
		this.initOilHigh = initOilHigh;
	}
	public Double getInitVolume() {
		return initVolume;
	}
	public void setInitVolume(Double initVolume) {
		this.initVolume = initVolume;
	}
	public Double getInitCriteriaVolume() {
		return initCriteriaVolume;
	}
	public void setInitCriteriaVolume(Double initCriteriaVolume) {
		this.initCriteriaVolume = initCriteriaVolume;
	}
	public Double getInitTemperature() {
		return initTemperature;
	}
	public void setInitTemperature(Double initTemperature) {
		this.initTemperature = initTemperature;
	}
	public Double getInitWaterHigh() {
		return initWaterHigh;
	}
	public void setInitWaterHigh(Double initWaterHigh) {
		this.initWaterHigh = initWaterHigh;
	}
	public Double getInitWaterValume() {
		return initWaterValume;
	}
	public void setInitWaterValume(Double initWaterValume) {
		this.initWaterValume = initWaterValume;
	}
	public Double getEndOilHigh() {
		return endOilHigh;
	}
	public void setEndOilHigh(Double endOilHigh) {
		this.endOilHigh = endOilHigh;
	}
	public Double getEndVolume() {
		return endVolume;
	}
	public void setEndVolume(Double endVolume) {
		this.endVolume = endVolume;
	}
	public Double getEndCriteriaVolume() {
		return endCriteriaVolume;
	}
	public void setEndCriteriaVolume(Double endCriteriaVolume) {
		this.endCriteriaVolume = endCriteriaVolume;
	}
	public Double getEndTemperature() {
		return endTemperature;
	}
	public void setEndTemperature(Double endTemperature) {
		this.endTemperature = endTemperature;
	}
	public Double getEndWaterHigh() {
		return endWaterHigh;
	}
	public void setEndWaterHigh(Double endWaterHigh) {
		this.endWaterHigh = endWaterHigh;
	}
	public Double getEndWaterValume() {
		return endWaterValume;
	}
	public void setEndWaterValume(Double endWaterValume) {
		this.endWaterValume = endWaterValume;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public String getPlanNo() {
		return planNo;
	}
	public void setLeakResult(Integer leakResult) {
		this.leakResult = leakResult;
	}
	public Integer getLeakResult() {
		return leakResult;
	}
	@Override
	public String pack() {
		
		return null;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setTankId(Long tankId) {
		this.tankId = tankId;
	}
	public Long getTankId() {
		return tankId;
	}
	
}
