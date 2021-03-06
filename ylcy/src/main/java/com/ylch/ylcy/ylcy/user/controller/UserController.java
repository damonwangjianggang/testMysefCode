package com.ylch.ylcy.ylcy.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ylch.ylcy.ylcy.user.bean.RequestBodyBean;
import com.ylch.ylcy.ylcy.user.bean.ResponseBean;
import com.ylch.ylcy.ylcy.user.bean.TokenBean;
import com.ylch.ylcy.ylcy.user.bean.VerificationCodeBean;
import com.ylch.ylcy.ylcy.user.bean.VersionBean;
import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoEntity;
import com.ylch.ylcy.ylcy.user.exception.DescribeException;
import com.ylch.ylcy.ylcy.user.exception.ExceptionEnum;
import com.ylch.ylcy.ylcy.user.exception.UnauthorizedException;
import com.ylch.ylcy.ylcy.user.service.UserService;
import com.ylch.ylcy.ylcy.user.util.IDCardValidateUtil;
import com.ylch.ylcy.ylcy.user.util.JWTUtil;
import com.ylch.ylcy.ylcy.user.util.redis.RediesClient;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RediesClient rediesClient;
	@Autowired
	private Environment env; 

	// 获取验证码，注册和忘记密码
	@PostMapping("/getVerificationCode")
	public ResponseBean getVerificationCode(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone =requestBodyBean.getPhone();
		String type = requestBodyBean.getType();
		if("".equals(phone)||"".equals(type)) {
			throw new DescribeException(ExceptionEnum.Null_point);
		}
		//验证码已存在
		if (rediesClient.isExistCheckCode(phone)) {
			return new ResponseBean(null, "0", "验证码已发送");
		}
		// o 表示用户值为0或1或2，0代表用户注册，1代表用户重置密码，2代表用户更换手机号
		if ("0".equals(type) && userService.verifyPhoneExist(phone)) {
			return new ResponseBean(null, "0", "手机号码已存在");
		} else if ("1".equals(type) && (!userService.verifyPhoneExist(phone))) {
			return new ResponseBean(null, "0", "手机号码不存在");
		}else if("2".equals(type)&&userService.verifyPhoneExist(phone)) {
			return new ResponseBean(null, "0", "手机号码已存在");
		}
		int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
		// 发送验证码
		String responseCode = userService.getVerificationCode(phone, type, mobile_code);
		//将验证码放入redis中
		rediesClient.saveCheckCode(phone, String.valueOf(mobile_code));
		if (responseCode.startsWith("0")) {
			return new ResponseBean(null, "1", "验证码发送成功");
		} else {
			return new ResponseBean(null, "0", "系统错误，验证码发送失败");
		}
	}
	
	// 用于更换手机号
		@PostMapping("/getVerificationCodeForChangePhone")
		public ResponseBean getVerificationCodeForChangePhone(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
			String phone =requestBodyBean.getPhone();
			String access_token=request.getHeader("access_token");
			if(!JWTUtil.verify(access_token, JWTUtil.getUsername(access_token), userService.getPasswordByPhone(JWTUtil.getUsername(access_token)))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
			String type ="2";
			if("".equals(phone)) {
				throw new DescribeException(ExceptionEnum.Null_point);
			}
			//验证码已存在
			if (rediesClient.isExistCheckCode(phone)) {
				return new ResponseBean(null, "0", "验证码已发送");
			}
			// o 表示用户值为0或1或2，0代表用户注册，1代表用户重置密码，2代表用户更换手机号
			 if(userService.verifyPhoneExist(phone)) {
				return new ResponseBean(null, "0", "手机号码已存在");
			}
			int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
			// 发送验证码
			String responseCode = userService.getVerificationCode(phone, type, mobile_code);
			//将验证码放入redis中
			rediesClient.saveCheckCode(phone, String.valueOf(mobile_code));
			if (responseCode.startsWith("0")) {
				return new ResponseBean(null, "1", "验证码发送成功");
			} else {
				return new ResponseBean(null, "0", "系统错误，验证码发送失败");
			}
		}

	// 用户注册
	// 用户使用手机号码注册
	@PostMapping("/registerUser")
	/*public ResponseBean userRegister(@RequestParam String phone, @RequestParam String password,
			@RequestParam String mobile_code) {*/
		public ResponseBean userRegister(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone=requestBodyBean.getPhone();
		String password =requestBodyBean.getPassword();
	    String mobile_code =requestBodyBean.getMobile_code();
	    
	    if("".equals(phone)||"".equals(password)||"".equals(mobile_code)) {
			throw new DescribeException(ExceptionEnum.Null_point);
		}
		if (rediesClient.isExistCheckCode(phone, mobile_code)) {
			userService.insertUser(phone, password);
			return new ResponseBean(null, "1", "注册成功");
		} else {
			return new ResponseBean(null, "0", "验证码已存在");
		}
	}

	// 更换手机号, 手机号作为车友用户表得唯一表示user userid
	@PostMapping("/userChangePhone")
	public ResponseBean changeUserName(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String newPhone=requestBodyBean.getNewPhone();
	    String originalPhone =requestBodyBean.getOriginalPhone();
	    String mobile_code = requestBodyBean.getMobile_code();
	    String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, originalPhone, userService.getPasswordByPhone(originalPhone))) {
			return new ResponseBean(null, "07", "登陆过期，请重新登陆");
		}
	    if("".equals(newPhone)||"".equals(originalPhone)||"".equals(mobile_code)) {
			throw new DescribeException(ExceptionEnum.Null_point);
		}
	    //UserEntity userEntityToken =userService.getCurrentUser();
		UserEntity userEntityByUserName =userService.getUserEntity(originalPhone);
		 
		if (rediesClient.isExistCheckCode(newPhone, mobile_code)) {
			userService.updateUserPhone(newPhone, originalPhone);
			return new ResponseBean(null, "1", "手机号码更换成功");
		} else {
			return new ResponseBean(null, "0", "手机号码更改失败");
		}
	}

	// 用户登陆
	@PostMapping("/loginUser")
	public ResponseBean userLogin(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone=requestBodyBean.getPhone();
		String password = requestBodyBean.getPassword();
		if(!userService.verifyPhoneExist(phone)) {
			return new ResponseBean(null, "0", "用户不存在");
		}else if(!password.equals(userService.getPasswordByPhone(phone))){
			return new ResponseBean(null, "0", "密码不正确");
		}else {
			UserInfoEntity userInfoEntity = userService.getUserInfo(phone);
			userInfoEntity.setAccess_token(JWTUtil.sign(phone, password));
			return new ResponseBean(userInfoEntity, "1", "Login success");
		}
		/*if (userService.verifyPhoneExist(phone) && password.equals(userService.getPasswordByPhone(phone))) {
			UserInfoEntity userInfoEntity = userService.getUserInfo(phone);
			userInfoEntity.setAccess_token(JWTUtil.sign(phone, password));
			return new ResponseBean(userInfoEntity, "1", "Login success");
		} else {
			return new ResponseBean(null, "0", "Login field");
		}*/
	}

	// 忘记密码
	@PostMapping("/forgetPassword")
	public ResponseBean forgetPassword(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone = requestBodyBean.getPhone();
		String password =requestBodyBean.getPassword();
		String mobile_code =requestBodyBean.getMobile_code();
		if (rediesClient.isExistCheckCode(phone, mobile_code)) {
			userService.updateUserPassword(phone, password);
			return new ResponseBean(null, "1", "resetPassword successed");
		} else {
			return new ResponseBean(null, "0", "resetPassword failed");
		}

	}

	// 更换密码
	@PostMapping("/changePassword")
	public ResponseBean changePassword(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone =requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		String originalPassword =requestBodyBean.getOriginalPassword();
		String newPassword =requestBodyBean.getNewPassword();
/*		UserEntity userEntityToken =userService.getCurrentUser();
*/		
		UserEntity userEntityToken=userService.getUserEntity(JWTUtil.getUsername(access_token));
		UserEntity userEntityByUserName =userService.getUserEntity(phone);
		if(originalPassword==null||!originalPassword.equals(userEntityByUserName.getPasswd())) {
			return new ResponseBean(null, "0", "原始密码不正确");
		}else if(userEntityToken!=null&&userEntityByUserName!=null
				&&phone!=null&&phone.equals(userEntityToken.getPhone())
				&&originalPassword.equals(userEntityByUserName.getPasswd())) {
					userService.updateUserPassword(phone, newPassword);
					return new ResponseBean(null, "1", "密码修改成功");
		}else {
			return new ResponseBean(null, "0", "change failed");
		}
	}

	// 上传用户头像
	@PostMapping("/uploadImage")
	public ResponseBean userChangePicture(@RequestParam MultipartFile uploadfile,  RequestBodyBean requestBodyBean,
			HttpServletRequest request) {
		String phone =requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		if(phone!=null&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			Object object =userService.uploadImage(uploadfile, phone, request);
			return new ResponseBean(null, "1", "uploadImage successed");
		}else {
			return new ResponseBean(null, "0", "uploadImage failed");
		}
	}

	// 更换用户呢称
	@PostMapping("/changeNickName")
	public ResponseBean changeNickName(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone= requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		String nickName = requestBodyBean.getNickName();
		if(phone!=null&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			userService.changeNickName(phone, nickName);
			return new ResponseBean(null, "1", "chanegNickName Successed");
		}else {
			return new ResponseBean(null, "0", "chanegNickName Failed");
		}
		
	}
	
	//得到个人信息
	@PostMapping("/getUserDetailInfo")
	public ResponseBean getUserDetailInfo(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String access_token=request.getHeader("access_token");
		String phone =requestBodyBean.getPhone();
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
			return new ResponseBean(null, "07", "登陆过期，请重新登陆");
		}	
		UserEntity userEntity = userService.getUserEntity(phone);
		if (userService.verifyPhoneExist(phone)
				&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			return new ResponseBean(userService.getUserPersionInfo(phone), "1", "loadPersionInfo Successed");
		} else {
			return new ResponseBean(null, "0", "loadPersionInfo Failed");
		}
	}
	
	//实名认证
	@PostMapping("/realNameAuthentication")
	public ResponseBean realNameAuthentication(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone =requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		String idCard =requestBodyBean.getIdCard();
		String realName =requestBodyBean.getRealName();
		if("".equals(phone)||"".equals(idCard)||"".equals(realName)) {
			throw new DescribeException(ExceptionEnum.Null_point);
		}else if(userService.IDCardIsExit(idCard)||!IDCardValidateUtil.validate(idCard)){
			throw new DescribeException(ExceptionEnum.Null_point);
		}
		if(IDCardValidateUtil.validate(idCard)&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			userService.realNameAuthentication(phone,realName,idCard);
			return new ResponseBean(null, "1", "AuthenticationRealName Sunncessed");
		}else {
			return new ResponseBean(null, "0", "AuthenticationRealName Failed");
		}
	}
	@PostMapping("/updateVsersion")
	public ResponseBean updateVsersion(HttpServletRequest request) {
		String os=request.getHeader("os");
		String app_version=request.getHeader("app_version");
		VersionBean version = new VersionBean();
		
		if("ios".equals(os)) {
			version.setVersionName(env.getProperty("android.version.name"));
			version.setVersionCode(env.getProperty("android.version.code"));
			version.setDownLoadURL(env.getProperty("android.downLoad.url"));
			return new ResponseBean(version, "1", "andrroid 版本信息获取成功");
		}else if("android".equals(os)) {
			version.setVersionName(env.getProperty("ios.version.code"));
			version.setVersionCode(env.getProperty("ios.version.name"));
			version.setDownLoadURL(env.getProperty("ios.downLoad。url"));
			return new ResponseBean(version, "1", "IOS 版本获取成功");
		}else {
			return new ResponseBean(null, "0", "版本信息获取成功");			
		}
	}
	/*// auth认证接口测试
	@PostMapping ("/getCuurentUseridORPhone")
	@RequiresAuthentication
	public  UserEntity getCuurentUseridORPhone() {
		return userService.getCurrentUser();
	}*/

	@GetMapping("/article")
	public ResponseBean article() {		
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return new ResponseBean(200, "You are already logged in", null);
		} else {
			return new ResponseBean(200, "You are guest", null);
		}
	}

	@GetMapping("/addUserAddress")
	public ResponseBean userAddAddress(@RequestParam String UserAddress) {
		return new ResponseBean("200", "login success");
	}

	@GetMapping("/addBrankNumber")
	public ResponseBean userAddBrankNumber() {
		return new ResponseBean("200", "login success");
	}

	/*@RequestMapping(path = "/401")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseBean unauthorized() {
		return new ResponseBean(null, "07", "请重新登陆");
	}*/

}

/*
 * @PostMapping("/userInfoVindicate")
	public ResponseBean VindicateUserInfo(@RequestParam String userid, @RequestParam String passwd,
			@RequestParam String realname, @RequestParam String nickname, @RequestParam String gender,
			@RequestParam String phone, @RequestParam String email, @RequestParam String census,
			@RequestParam String imgurl, @RequestParam String mrdz, @RequestParam String mryhk) {
		return new ResponseBean("200", "vindicate success");
	}
 * @GetMapping("/article") public ResponseBean article() { Subject subject =
 * SecurityUtils.getSubject(); if (subject.isAuthenticated()) { return new
 * ResponseBean(200, "You are already logged in", null); } else { return new
 * ResponseBean(200, "You are guest", null); } }
 * 
 * @GetMapping("/require_auth")
 * 
 * @RequiresAuthentication public ResponseBean requireAuth() { return new
 * ResponseBean(200, "You are authenticated", null); }
 * 
 * @PostMapping("/getUserInfo")
 * 
 * @RequiresAuthentication public ResponseBean getUserInfo(@RequestParam String
 * username) { if (username != null) { return new ResponseBean(200, "用户信息",
 * userService.getUserEntity(username)); } else { return new ResponseBean(200,
 * "请登陆", null); } }
 * 
 * 
 * @GetMapping("/view") public ResponseBean view() { Subject subject =
 * SecurityUtils.getSubject(); if(subject.isAuthenticated()){ return new
 * ResponseBean(200,"you are viewing now",null); } return new
 * ResponseBean(200,"you need Authenticated",null); }
 * 
 * @RequestMapping(path = "/401")
 * 
 * @ResponseStatus(HttpStatus.UNAUTHORIZED) public ResponseBean unauthorized() {
 * return new ResponseBean(401, "Unauthorized", null); }
 */
