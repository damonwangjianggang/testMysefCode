package com.ylch.ylcy.ylcy.home.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ylch.ylcy.ylcy.home.entitys.News;

public interface NewsService {
	List<News> getNewsByCount(int pageIndex,int pageSize);
	News getFullById(int id);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	void saveNews(News news);
}
