package com.ylch.ylcy.ylcy.common.entitys;

/**
 * 用于接收app端返回的各种数据
 * @author 12557
 *
 */
public class RsgContent {

	private int pageIndex;//页码
	private int pageSize;//每页大小
	
	private String news_id;//新闻ID
	private int com_id;//商品ID
	
	private String phone;//手机号
	private String order_id;//订单编号
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	
}
