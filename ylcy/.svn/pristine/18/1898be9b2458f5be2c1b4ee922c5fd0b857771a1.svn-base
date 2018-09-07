package com.ylch.ylcy.ylcy.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ylch.ylcy.ylcy.home.entitys.News;

/**  
* <p>Title: NewsDao</p>  
* <p>Description: 首页新闻模块持久层</p>  
* @author AntChenxi  
* @date 2018年5月29日  
*/
@Mapper
public interface NewsDao {

	/**
	 * 获取新闻列表
	 * @param count 需获取条数
	 * @return List 新闻列表
	 */
	@Select("select news_id,news_title,news_imgurl from news order by news_id desc limit #{begin}, #{pageSize} ")
	@Results(value = {
            @Result(property = "news_id",column = "news_id"),
            @Result(property = "news_title",column = "news_title"),
            @Result(property = "news_imgurl",column = "news_imgurl")
    })
	List<News> getNewsByCount(@Param("begin") int begin,@Param("pageSize") int pageSize);
	
	/**
	 * 获取一条新闻的全部信息
	 * @param id 新闻ID
	 * @return News对象
	 */
	@Select("select * from news where news_id = #{id}")
	News getFullById(@Param("id") int id);
	
	@Insert("insert into news(news_title,news_content,news_imgurl) values(#{news_title},#{news_content},#{news_imgurl})")
	void saveNews(News news);
}
