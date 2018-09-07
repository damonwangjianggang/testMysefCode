package com.ylch.ylcy.ylcy.shops.entitys;

/**
 * 商品类
 * @author 12557
 *
 */
public class Goods {
	private int com_id;
	private String com_name;
	private String com_price;
	private String com_imgurl;
	private String com_detail;
	private String com_jf;
	private int isTicket;//是否为虚拟货物
	private int stock;//库存

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getIsTicket() {
		return isTicket;
	}

	public void setIsTicket(int isTicket) {
		this.isTicket = isTicket;
	}

	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_price() {
		return com_price;
	}

	public void setCom_price(String com_price) {
		this.com_price = com_price;
	}

	public String getCom_imgurl() {
		return com_imgurl;
	}

	public void setCom_imgurl(String com_imgurl) {
		this.com_imgurl = com_imgurl;
	}

	public String getCom_detail() {
		return com_detail;
	}

	public void setCom_detail(String com_detail) {
		this.com_detail = com_detail;
	}

	public String getCom_jf() {
		return com_jf;
	}

	public void setCom_jf(String com_jf) {
		this.com_jf = com_jf;
	}

}