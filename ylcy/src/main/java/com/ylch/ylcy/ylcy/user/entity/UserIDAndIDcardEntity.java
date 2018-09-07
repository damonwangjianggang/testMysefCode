package com.ylch.ylcy.ylcy.user.entity;

import java.io.Serializable;

public class UserIDAndIDcardEntity implements Serializable{
	private String userId;
	private String idCard;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	

}
