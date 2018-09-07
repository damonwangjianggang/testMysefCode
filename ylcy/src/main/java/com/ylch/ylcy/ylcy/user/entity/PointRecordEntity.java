package com.ylch.ylcy.ylcy.user.entity;

import java.io.Serializable;


public class PointRecordEntity implements Serializable {
	public String getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(String repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	public String getIntegralNumerical() {
		return integralNumerical;
	}
	public void setIntegralNumerical(String integralNumerical) {
		this.integralNumerical = integralNumerical;
	}
	private String userid ;//用户ID
	private String recordId;//订单ID
	private String type;//类型
	private String time;//操作时间
	private String reason;//操作原因
	private String repaymentAmount;//还款总额
	private String integralNumerical;//积分值

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
