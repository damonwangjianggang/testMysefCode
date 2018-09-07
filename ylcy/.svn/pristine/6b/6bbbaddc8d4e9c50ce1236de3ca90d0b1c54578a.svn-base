package com.ylch.ylcy.ylcy.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoForBuyGoods;
import com.ylch.ylcy.ylcy.user.entity.PersionInfoEntity;

@Service
public interface UserService {
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	int  insertUser(String userName,String passWord);
	
	public UserEntity getUserEntity(String userName);
	public PersionInfoEntity getUserPersionInfo(String username);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	int updateUserPassword(String phone,String passWord);
	String verifyUserName(String userName);
	Object uploadImage(MultipartFile file ,String userName,HttpServletRequest request);
	public String getVerificationCode(String mobile,String type,int mobile_code);
	int changeNickName(String userName,String nickName);
	// 检查phone是否存在
	boolean verifyPhoneExist(String phone);
	public String  getPasswordByPhone(String phone);
	
	//更新用户信息
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	int updateUserPhone(String NewPhone,String originalPhone);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	int realNameAuthentication(String phone, String realName, String idCard);
	public UserEntity getCurrentUser(); 
	public UserInfoEntity getUserInfo(String phone); 
	//购买商品所需用户信息
	public UserInfoForBuyGoods getUserInfoForBuyGoods(String phone);
	
	List<String> getAllUserid();

	boolean IDCardIsExit(String idCard);
	
}
