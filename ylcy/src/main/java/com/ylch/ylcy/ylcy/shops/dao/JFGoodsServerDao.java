package com.ylch.ylcy.ylcy.shops.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ylch.ylcy.ylcy.shops.entitys.Goods;

@Mapper
public interface JFGoodsServerDao {

	/**
	 * 保存商品信息
	 * @param paramGoods
	 */
	@Insert({
			"INSERT INTO commodity(com_name,com_price,com_imgurl,com_detail,com_jf,isTicket) VALUES(#{com_name},#{com_price},#{com_imgurl},#{com_detail},#{com_jf},#{isTicket})" })
	public void saveGoods(Goods goods);
	
}
