package com.ylch.ylcy.ylcy.user.entity;

public class PersionInfoEntity {
private String	  realname;// 真实姓名
private String	  nickname;//呢称
private String	  phone;//手机号
private String	  idcard;//身份证号
private String    imgurl;//图片信息
private String    pointTotal;//用户总积分

public String getImgurl() {
	return imgurl;
}
public void setImgurl(String imgurl) {
	this.imgurl = imgurl;
}
public String getPointTotal() {
	return pointTotal;
}
public void setPointTotal(String pointTotal) {
	this.pointTotal = pointTotal;
}
public String getRealname() {
	return realname;
}
public void setRealname(String realname) {
	this.realname = realname;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getIdcard() {
	return idcard;
}
public void setIdcard(String idcard) {
	this.idcard = idcard;
}
@Override
public String toString() {
	return "PersionInfoEntity [realname=" + realname + ", nickname=" + nickname + ", phone=" + phone + ", idcard="
			+ idcard + ", imgurl=" + imgurl + ", pointTotal=" + pointTotal + "]";
}


}
