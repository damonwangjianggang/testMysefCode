package com.ylch.ylcy.ylcy.shops.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ylch.ylcy.ylcy.common.entitys.PageBean;
import com.ylch.ylcy.ylcy.common.entitys.RsgContent;
import com.ylch.ylcy.ylcy.shops.entitys.Goods;
import com.ylch.ylcy.ylcy.shops.entitys.GoodsTicket;
import com.ylch.ylcy.ylcy.user.entity.PlatformOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PointRecordEntity;

@Mapper
public abstract interface JFGoodsDao {
	/**
	 * 查询商品带分页
	 * @param paramPageBean
	 * @return
	 */
	@Select({ "SELECT * FROM commodity limit #{begin},#{eachPageSize}" })
	@Results({ @Result(property = "com_id", column = "com_id"), 
			@Result(property = "com_name", column = "com_name"),
			@Result(property = "com_price", column = "com_price"),
			@Result(property = "com_imgurl", column = "com_imgurl"),
			@Result(property = "com_detail", column = "com_detail"), 
			@Result(property = "com_jf", column = "com_jf") })
	public List<Goods> selectGoodsPage(PageBean pageBean);

	/**
	 * 查询商品
	 * @return
	 */
	@Select({ "SELECT * FROM commodity" })
	@Results({ @Result(property = "com_id", column = "com_id"), 
			@Result(property = "com_name", column = "com_name"),
			@Result(property = "com_price", column = "com_price"),
			@Result(property = "com_imgurl", column = "com_imgurl"),
			@Result(property = "com_detail", column = "com_detail"), 
			@Result(property = "com_jf", column = "com_jf") })
	public List<Goods> selectGoods();

	/**
	 * 查询商品详情信息
	 * @param paramRsgContent
	 * @return
	 */
	@Select({ "SELECT * FROM commodity where com_id=#{com_id}" })
	@Results({ @Result(property = "com_id", column = "com_id"), 
			@Result(property = "com_name", column = "com_name"),
			@Result(property = "com_price", column = "com_price"),
			@Result(property = "com_imgurl", column = "com_imgurl"),
			@Result(property = "com_detail", column = "com_detail"), 
			@Result(property = "com_jf", column = "com_jf") })
	public Goods selectGoodById(RsgContent rsgContent);
	
	/**
	 * 保存积分记录
	 */
	@Insert({ 
			"INSERT INTO point_record(userid,record_id,type,time,reason,integral_numerical,repayment_amount) VALUES(#{userid},#{recordId},#{type},#{time},#{reason},#{integralNumerical},#{repaymentAmount})" })
	public void savePointRecord(PointRecordEntity pointRecordEntity);
	
	/**
	 * 查询一条卷码记录
	 */
	@Select({
			"SELECT * FROM ticket WHERE com_id=#{com_id} and status=#{status} limit 1" })
	@Results({
			@Result(property = "t_id",column = "t_id"),
			@Result(property = "code",column = "code"),
			@Result(property = "company_id",column = "company_id"),
			@Result(property = "status",column = "status"),
			@Result(property = "userid",column = "userid"),
			@Result(property = "com_id",column = "com_id")})
	public GoodsTicket selCode(@Param("com_id")int com_id,@Param("status")String status);
	
	/**
	 * 更新卷码状态
	 */
	@Update({
			"UPDATE ticket SET status='0' WHERE t_id=#{t_id} "})
	public void updateCodeStatus(GoodsTicket gtTicket);
	
	/**
	 * 保存订单记录
	 */
	@Insert({ 
			"INSERT INTO user_order(userid,order_id,order_addr,com_id,order_status) VALUES(#{userid},#{orderId},#{orderAddr},#{comId},#{orderStatus})" })
	public void savePfOrderInfo(PlatformOrderEntity pfRecordEntity);
	
	/**
	 * 根据订单id查询订单
	 */
	@Select({
			"SELECT * FROM user_order WHERE order_id=#{orderId}"})
	@Results({
			@Result(property = "userid",column = "userid"),
			@Result(property = "orderId",column = "order_id"),
			@Result(property = "orderAddr",column = "order_addr"),
			@Result(property = "comId",column = "com_id"),
			@Result(property = "orderStatus",column = "order_status")})
	public PlatformOrderEntity selPfOrderInfoById(@Param("orderId")String orderId);
	
	/**
	 * 实物商品直接更新库存
	 */
	@Update({
			 "UPDATE commodity SET stock=#{stock} where com_id=#{com_id}" })
	public void updateStockNum(@Param("stock")int stock,@Param("com_id")int com_id);
	
	/**
	 * 虚拟货物通过商品id查询对应url路径
	 */
	@Select({
			"SELECT TC.url FROM ticket_company TC LEFT JOIN ticket T ON TC.company_id=T.company_id WHERE T.com_id=#{com_id} limit 1" })
	@Results({
			@Result(property = "url",column = "url")})
	public String selCompanyUrl(@Param("com_id")int com_id);
}