package com.oarage.lsdc.commons.entity;

import java.sql.Timestamp;

/**
 * 控制台应答信息
 * 
 * @author changwei
 * @date 2013-6-7上午10:04:24
 * 
 */
public class UploadResponseData implements IParameter {

	private int id;
	private String stationCode;
	private int msgId;// 信息ID
	private int responseCode;// 应答类型代码
	private int responseStatus;// 应答状态
	private int paramCount;// 参数数量
	private String paramContent;// 更新参数内容
	private Timestamp insertTime;
	
	/****应答类型代码含义****/
	public final static int RESPONSE_CODE_UPLOAD_INTERVAL = 1;//间隔上传参数下发
	public final static int RESPONSE_CODE_WAVE_UPLOAD = 2;//波动上传参数下发
	public final static int RESPONSE_CODE_TIMER_UPLOAD = 3;//定时上传参数下发
	public final static int RESPONSE_CODE_TABLE_UPDATE = 4;//容积表更新
	public final static int RESPONSE_CODE_LEAK_STATUS_UPDATE = 5;//测漏状态变更
	
	/****应答状态含义****/
	public final static int RESPONSE_STATUS_PARAM_SEND_SUCCESS = 0;//参数下发成功
	public final static int RESPONSE_STATUS_PARAM_UPDATE_SUCCESS = 1;//参数更新成功
	public final static int RESPONSE_STATUS_TABLE_UPDATE_FAIL = 2;//参数更新失败（只针对容积表）

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

	public int getParamCount() {
		return paramCount;
	}

	public void setParamCount(int paramCount) {
		this.paramCount = paramCount;
	}

	public String getParamContent() {
		return paramContent;
	}

	public void setParamContent(String paramContent) {
		this.paramContent = paramContent;
	}

	@Override
	public String pack() {
		return null;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}
}
