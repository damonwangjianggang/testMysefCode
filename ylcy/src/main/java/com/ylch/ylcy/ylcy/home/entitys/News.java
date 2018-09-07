package com.ylch.ylcy.ylcy.home.entitys;

public class News {
	private String news_id;
	private String news_title;
	private String news_content;
	private String news_imgurl;
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public String getNews_imgurl() {
		return news_imgurl;
	}
	public void setNews_imgurl(String news_imgurl) {
		this.news_imgurl = news_imgurl;
	}
	@Override
	public String toString() {
		return "News [news_id=" + news_id + ", news_title=" + news_title + ", news_content=" + news_content
				+ ", news_imgurl=" + news_imgurl + "]";
	}
	

	
}
