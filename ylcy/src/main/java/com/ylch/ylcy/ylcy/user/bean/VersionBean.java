package com.ylch.ylcy.ylcy.user.bean;

import java.io.Serializable;

public class VersionBean implements Serializable {

	private String versionCode;
	private String versionName;
	private String downLoadURL;
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getDownLoadURL() {
		return downLoadURL;
	}
	public void setDownLoadURL(String downLoadURL) {
		this.downLoadURL = downLoadURL;
	}
	
}
