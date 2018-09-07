package com.ylch.ylcy.ylcy.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ylch.ylcy.ylcy.user.bean.RequestBodyBean;
import com.ylch.ylcy.ylcy.user.bean.ResponseBean;
import com.ylch.ylcy.ylcy.user.entity.IntegralDetailEntity;
import com.ylch.ylcy.ylcy.user.entity.IntegralEntity;
import com.ylch.ylcy.ylcy.user.entity.PointRecordEntity;
import com.ylch.ylcy.ylcy.user.service.IntegralService;
import com.ylch.ylcy.ylcy.user.service.UserService;
import com.ylch.ylcy.ylcy.user.util.JWTUtil;

@RestController
public class IntegralController {
	@Autowired
	private IntegralService integralService;
	@Autowired
	private UserService userService;

	
	@PostMapping("/getTotalIntegralByUserid")
	public ResponseBean getTotalIntegralByUserid(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone =requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		if(phone!=null&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			Map<String,IntegralEntity> map = new HashMap<String,IntegralEntity>();
			map.put("dataList",integralService.getIntegralByUserid(phone));
			return new ResponseBean(map,"1","GetTotalIntegral Successed");

		}else {
			return new ResponseBean(null,"0","GetTotalIntegral Successed");
		}
		
	}
	
	@PostMapping("/getIntegralRecordByUserid")
	public ResponseBean getIntegralRecordByUserid(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone =requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		if(phone!=null&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			Map<String,List<PointRecordEntity>> map = new HashMap<String,List<PointRecordEntity>>();
			map.put("dataList", integralService.getIntegralRecordDetailByUserid(phone));
			return new ResponseBean(map,"1","getIntegralRecord Success");
		}else {
			return new ResponseBean(null,"0","getIntegralRecord failed");
		}
	}
	}
