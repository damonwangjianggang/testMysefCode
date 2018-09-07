package com.ylch.ylcy.ylcy.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ylch.ylcy.ylcy.common.entitys.RsgContent;
import com.ylch.ylcy.ylcy.home.entitys.News;
import com.ylch.ylcy.ylcy.home.service.NewsService;
import com.ylch.ylcy.ylcy.user.bean.ResponseBean;
import com.ylch.ylcy.ylcy.user.service.UserService;
import com.ylch.ylcy.ylcy.user.util.JWTUtil;

/**
 * <p>
 * Title: NewsController
 * </p>
 * <p>
 * Description: 新闻模块控制器
 * </p>
 * 
 * @author AntChenxi
 * @date 2018年5月29日
 */
@RestController
public class NewsController {

	@Autowired
	private NewsService service;
	
	@Autowired
	private UserService userService;

	/**
	 * 获得新闻列表
	 * 
	 * @param request
	 *            http请求
	 * @return
	 */
	@RequestMapping("/getSimpleNewsList")
	public Object getNewsByCount(HttpServletRequest request, @RequestBody RsgContent rsgContent) {
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, JWTUtil.getUsername(access_token), userService.getPasswordByPhone(JWTUtil.getUsername(access_token)))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> newsList = new HashMap<>();
		List<News> list = null;
		list = service.getNewsByCount(rsgContent.getPageIndex(), rsgContent.getPageSize());
		newsList.put("newsList", list);
		map.put("data", newsList);
		map.put("resultCode", "1");
		map.put("resultMessage", "");
		return map;
	}

	/**
	 * 根据新闻ID获取新闻全部内容
	 * 
	 * @param request
	 * @return News对象
	 */
	@RequestMapping("/getFullNew")
	public Object getFullById(HttpServletRequest request, @RequestBody RsgContent rsgContent) {
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, JWTUtil.getUsername(access_token), userService.getPasswordByPhone(JWTUtil.getUsername(access_token)))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		
		Map<String, Object> map = new HashMap<>();
		News news = null;
		String sid = rsgContent.getNews_id();
		if (sid == null || sid.equals("0") || sid == "") {
			map.put("data", null);
			map.put("resultCode", -1);
			map.put("resultMessage", "传入ID不能为空或零!!");
		} else {
			int id = Integer.parseInt(sid);
			news = service.getFullById(id);
			map.put("data", news);
			map.put("resultCode", "1");
			map.put("resultMessage", "");
		}
		return map;
	}

}
