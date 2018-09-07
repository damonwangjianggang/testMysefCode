package com.ylch.ylcy.ylcy.user.bean;

public class TokenBean {
	private String Authorization;

	public String getAuthorization() {
		return Authorization;
	}

	public void setAuthorization(String authorization) {
		Authorization = authorization;
	}

	public TokenBean(String authorization) {
		super();
		Authorization = authorization;
	}
	

}
