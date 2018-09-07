package com.ylch.ylcy.ylcy.user.entity;

import java.io.Serializable;

public class UserEntity implements Serializable{
	private String userid;
	private String phone;
	private String passwd;
	private String role;
	private String permission;
	private String azjhm;
	private String akhxm;
	
	public String getAkhxm() {
		return akhxm;
	}
	public void setAkhxm(String akhxm) {
		this.akhxm = akhxm;
	}
	public String getAzjhm() {
		return azjhm;
	}
	public void setAzjhm(String azjhm) {
		this.azjhm = azjhm;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPassword(String passwd) {
		this.passwd = passwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
