package com.ylch.ylcy.ylcy.home.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylch.ylcy.ylcy.home.dao.NewsDao;
import com.ylch.ylcy.ylcy.home.entitys.News;
import com.ylch.ylcy.ylcy.home.service.NewsService;

@Service("NewsService")
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDao dao;

	@Override
	public List<News> getNewsByCount(int pageIndex,int pageSize) {
		if (pageIndex <= 0) {
			pageIndex = 1;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}
		int begin = pageSize * (pageIndex - 1);
		return dao.getNewsByCount(begin,pageSize);
	}

	@Override
	public News getFullById(int id) {
		return dao.getFullById(id);
	}

	@Override
	public void saveNews(News news) {
		dao.saveNews(news);
	}

}
