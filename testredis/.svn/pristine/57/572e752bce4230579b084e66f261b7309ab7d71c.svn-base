package com.oarage.lsdc.commons.entity;

import java.util.Date;

/**
 * Title:开关机信息
 *
 * @author wujh
 *
 * @create on 2013-10-28
 *
 * @version
 *
 */
public class StartUpData implements IParameter
{

  private Long id;

  private String stationCode;

  /*
   * 开机时间
   */
  private Date startUpTime;

  /*
   * 关机时间
   */
  private Date shutDownTime;

  private Long stationId;

  private String stationShortName;

  private String stationFullName;

  private Long orgId;

  private String orgCode;

  private String orgName;

  private int pOrgId;

  private String pOrgCode;

  private String pOrgName;

  public int getpOrgId() {
    return pOrgId;
  }

  public void setpOrgId(int pOrgId) {
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

  private Date insertTime;

  public Date getNextStartupTime() {
    return nextStartupTime;
  }

  public void setNextStartupTime(Date nextStartupTime) {
    this.nextStartupTime = nextStartupTime;
  }

  private Date nextStartupTime;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Date getInsertTime()
  {
    return insertTime;
  }

  public void setInsertTime(Date insertTime)
  {
    this.insertTime = insertTime;
  }

  public String getStationCode()
  {
    return stationCode;
  }

  public void setStationCode(String stationCode)
  {
    this.stationCode = stationCode;
  }

  public Date getStartUpTime()
  {
    return startUpTime;
  }

  public void setStartUpTime(Date startUpTime)
  {
    this.startUpTime = startUpTime;
  }

  public Date getShutDownTime()
  {
    return shutDownTime;
  }

  public void setShutDownTime(Date shutDownTime)
  {
    this.shutDownTime = shutDownTime;
  }

  public Long getStationId()
  {
    return stationId;
  }

  public void setStationId(Long stationId)
  {
    this.stationId = stationId;
  }

  public String getStationShortName()
  {
    return stationShortName;
  }

  public void setStationShortName(String stationShortName)
  {
    this.stationShortName = stationShortName;
  }

  public String getStationFullName()
  {
    return stationFullName;
  }

  public void setStationFullName(String stationFullName)
  {
    this.stationFullName = stationFullName;
  }

  public Long getOrgId()
  {
    return orgId;
  }

  public void setOrgId(Long orgId)
  {
    this.orgId = orgId;
  }

  public String getOrgCode()
  {
    return orgCode;
  }

  public void setOrgCode(String orgCode)
  {
    this.orgCode = orgCode;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  @Override
  public String pack()
  {
    return null;
  }

  //2019.8.27 新增异常状态 0 正常 1异常  新增关机时长
  private int addition;

  public int getAddition() {
    return addition;
  }

  public void setAddition(int addition) {
    this.addition = addition;
  }

  private double shutdownTimeLength;

  public double getShutdownTimeLength() {
    return shutdownTimeLength;
  }

  public void setShutdownTimeLength(double shutdownTimeLength) {
    this.shutdownTimeLength = shutdownTimeLength;
  }
}
