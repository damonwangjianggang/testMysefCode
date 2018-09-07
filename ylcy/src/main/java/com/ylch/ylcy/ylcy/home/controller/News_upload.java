package com.ylch.ylcy.ylcy.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ylch.ylcy.ylcy.home.entitys.News;
import com.ylch.ylcy.ylcy.home.service.NewsService;

/**  
* <p>Title: News_upload</p>  
* <p>Description: 新闻上传类</p>  
* @author AntChenxi  
* @date 2018年5月31日  
*/
@Controller
public class News_upload {
	
	@Autowired
	private NewsService service;
	
	/**
	 * 跳转到新闻上传页
	 * @return
	 */
	@RequestMapping("/getNewsUploadPage")
	public String getNewsUploadPage() {
		//System.out.println("i am in!");
		/*
		
			留作登录校验
		
		*/
		return "news_update";
	}
	
	
	/**
	 * 保存新闻方法
	 * @param news
	 * @return
	 */
	@RequestMapping("/saveNews")
	public String saveNews(News news) {
		//System.out.println(news.toString());
		service.saveNews(news);
		return "news_update";
	}
	
	
	/**
	 * 通过新闻ID获取新闻内容
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("/showNewsById")
	public String getFullById(Model m,HttpServletRequest request) {
		News news = null;
		if (request != null) {
			String news_id = request.getParameter("news_id");
			news = service.getFullById(Integer.parseInt(news_id));
		}
		m.addAttribute("news", news);
		return "shownews";
	}
	
	

}
