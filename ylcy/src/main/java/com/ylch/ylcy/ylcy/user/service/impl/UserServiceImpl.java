package com.ylch.ylcy.ylcy.user.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ylch.ylcy.ylcy.user.dao.UserDao;
import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoForBuyGoods;
import com.ylch.ylcy.ylcy.user.entity.PersionInfoEntity;
import com.ylch.ylcy.ylcy.user.service.UserService;
import com.ylch.ylcy.ylcy.user.util.HttpClientUtileForSMSGETRequest;
import com.ylch.ylcy.ylcy.user.util.JWTUtil;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private Environment env;
	@Autowired
	private UserDao userDao;

	@Override
	public int insertUser(String phone,String passWord) {
		String userid=phone;
		return userDao.insertUser(userid,passWord,phone);
	}

	@Override
	public UserEntity getUserEntity(String userName) {
			
		return userDao.getUser(userName);
	}

	@Override
	public PersionInfoEntity getUserPersionInfo(String username) {
		return userDao.getUserPersionInfo(username);
	}

	@Override
	public int updateUserPassword(String phone, String passWord) {
		return userDao.updateUserPassword(phone, passWord);
	}

	@Override
	public String verifyUserName(String userName) {
		String  result =userDao.verifyUser(userName);
		if(null==result) {
			return "not exist";
		}else {
			return "exist";
		}
	}

	@Override
	public Object uploadImage(MultipartFile file, String phone,HttpServletRequest request) {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		if (file != null) {
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					// 当前app根目录
					String rootPath = request.getServletContext().getRealPath("/");

					// 需要上传的相对地址（application.properties中获取）
					String relativePath = env.getProperty("image.file.upload.dir");

					// 文件夹是否存在，不存在就创建
					File dir = new File(rootPath + File.separator + relativePath);
					if (!dir.exists())
						dir.mkdirs();
					String fileExtension = getFileExtension(file);

					// 生成UUID样式的文件名
					String filename = java.util.UUID.randomUUID().toString() + "." + fileExtension;

					// 文件全名
					String fullFilename = dir.getAbsolutePath() + File.separator + filename;

					// 用户头像被访问路径
//					String relativeFile = relativePath + File.separator + filename;
					String relativeFile = relativePath +  filename;
					// 保存图片
					File serverFile = new File(fullFilename);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					LOGGER.info("Server File Location = " + serverFile.getAbsolutePath());
					
					
					String serverPath = new URL(request.getScheme(), request.getServerName(), request.getServerPort(),
							request.getContextPath()).toString();
					ret.put("url", serverPath + "/" + relativeFile);
					userDao.uploadImage(phone, relativeFile);
					//user.setImage(relativeFile);
					//userRepository.save(user);

				} catch (Exception e) {
					LOGGER.info("error: {}", e);
					ret.put("url", "none");
				}
			}
		}
		return null;

	}

	/**
	 * 返回文件后缀名，如果有的话
	 */
	public static String getFileExtension(MultipartFile file) {
		if (file == null) {
			return null;
		}

		String name = file.getOriginalFilename();
		int extIndex = name.lastIndexOf(".");

		if (extIndex == -1) {
			return "";
		} else {
			return name.substring(extIndex + 1);
		}
	}

	@Override
	public String  getVerificationCode(String mobile, String type,int mobile_code) {
		
		String content= null;
		if("0".equals(type)) {
			content="您正在注册，您的验证码是: "+mobile_code;
		}else if("1".equals(type)) {
			content="您正在重置密码， 您的验证码是： "+mobile_code;
		}else if("2".equals(type)) {
			content="您正在跟换手机号码，您得验证码是： "+mobile_code;
		}else {
			content ="您的验证码是： "+mobile_code;
		}
		return HttpClientUtileForSMSGETRequest.doGetRequest(mobile, content);	
	}

	@Override
	public int  changeNickName(String phone, String nickName) {

		return userDao.changeNickName(phone, nickName);
	}



	

	@Override
	public boolean verifyPhoneExist(String phone) {
		String isPhoneExist =userDao.verifyPhoneExist(phone);
		if(null==isPhoneExist) {
			return false;
		}else {
			return true;	
		}
	}

	@Override
	public int  updateUserPhone(String phone,String originalPhone) {
		 return userDao.updatePhone(phone,originalPhone);
		
	}

	@Override
	public String getPasswordByPhone(String phone) {

		return userDao.getPasswordByPhone(phone);
	}

	@Override
	public int  realNameAuthentication(String phone, String realName, String idCard) {
		return userDao.insertRealName( phone,realName,  idCard);
	}

	@Override
	public UserEntity getCurrentUser() {
		return userDao.findUserByCurrentUser(getCurrentUserid());
	}

	private String getCurrentUserid() {
		String userid = JWTUtil.getUsername(SecurityUtils.getSubject().getPrincipal().toString());		
		return userid;
	}

	@Override
	public UserInfoEntity getUserInfo(String phone) {
		return userDao.getUserInfo(phone);
	}

	@Override
	public List<String> getAllUserid() {
		return userDao.getAllUserid();
	}

	@Override
	public boolean IDCardIsExit(String idCard) {
		int IDcardIndex = userDao.IDCardIsExit(idCard);
		if(IDcardIndex>0) {
			return true ;
		}else {
			return false;		
		}
	}

	@Override
	public UserInfoForBuyGoods getUserInfoForBuyGoods(String phone) {
		return userDao.getUserInfoForBuyGoods(phone);
	}
	

}
