package com.ylch.ylcy.ylcy.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ylch.ylcy.ylcy.user.bean.OriginalOrderPageOneBean;
import com.ylch.ylcy.ylcy.user.bean.OriginalOrderPageTwoBean;
import com.ylch.ylcy.ylcy.user.bean.RequestBodyBean;
import com.ylch.ylcy.ylcy.user.bean.ResponseBean;
import com.ylch.ylcy.ylcy.user.bean.ResponseDateBean;
import com.ylch.ylcy.ylcy.user.entity.OriginalOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PlatformOrderEntity;
import com.ylch.ylcy.ylcy.user.service.OrderService;
import com.ylch.ylcy.ylcy.user.service.UserService;
import com.ylch.ylcy.ylcy.user.util.JWTUtil;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@PostMapping("/getPlatformOrder")
	public ResponseBean getPlatformOrder(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone = requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		if(phone!=null&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
				Map<String, List<PlatformOrderEntity>> map = new  HashMap<String,List<PlatformOrderEntity>>();
				map.put("dataList", orderService.getPlatformOrder(phone));
			return new ResponseBean(map,"1","GetPlatformOrder Successed");

		}else {
			return new ResponseBean(null,"0","GetPlatformOrder Successed");
		}
		
	}
	@PostMapping("/getOriginalOrder")
	public ResponseBean getOriginalOrder(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone =requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		if(phone!=null&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			Map<String,List<OriginalOrderEntity>> map = new  HashMap<String,List<OriginalOrderEntity>>();
			map.put("dataList", orderService.getOriginalOrder(phone));
			return new ResponseBean(map,"1","GetOriginalOrder Successed");
		}else {
			return new ResponseBean(null,"0","GetOriginalOrder Successed");
		}
	}
	@PostMapping("/getOriginalOrderPageOne")
	public ResponseBean getOriginalOrderPageOne(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone =requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		if(phone!=null&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			Map<String,List<OriginalOrderPageOneBean>> map = new  HashMap<String,List<OriginalOrderPageOneBean>>();
			map.put("dataList", orderService.getOriginalOrderPageOne(phone));
			return new ResponseBean(map,"1","GetOriginalOrder Successed");
		}else {
			return new ResponseBean(null,"0","GetOriginalOrder Successed");
		}
	}
	@PostMapping("/getOriginalOrderPageTwo")
	public ResponseBean getOriginalOrderPageTwo(HttpServletRequest request, @RequestBody RequestBodyBean requestBodyBean) {
		String phone =requestBodyBean.getPhone();
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, phone, userService.getPasswordByPhone(phone))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		String asqbh =requestBodyBean.getAsqbh();
		if(phone!=null&&phone.equals(userService.getUserEntity(JWTUtil.getUsername(access_token)).getPhone())) {
			Map<String,OriginalOrderPageTwoBean> map = new  HashMap<String,OriginalOrderPageTwoBean>();
			map.put("dataList", orderService.getOriginalOrderPageTwo(phone,asqbh));
			return new ResponseBean(map,"1","GetOriginalOrder Successed");
		}else {
			return new ResponseBean(null,"0","GetOriginalOrder Successed");
		}
	}
}
