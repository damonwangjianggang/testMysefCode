package com.ylch.ylcy.ylcy.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ylch.ylcy.ylcy.user.bean.OriginalOrderByStagesRecordBean;
import com.ylch.ylcy.ylcy.user.bean.OriginalOrderPageOneBean;
import com.ylch.ylcy.ylcy.user.entity.OriginalOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PlatformOrderEntity;

@Mapper
public interface OrderDao {
	@Select("select u.userid,u.order_id,u.order_addr,u.com_id,u.order_status,c.com_name,c.com_imgurl from user_order u"
			+ " left join commodity c on u.com_id = c.com_id where u.userid =#{username}")
	@Results({@Result(property = "userid", column = "userid"),
		@Result(property = "orderId", column = "order_id"),
		@Result(property = "orderAddr", column = "order_addr"),
		@Result(property = "comId", column = "com_id"),
		@Result(property = "orderStatus", column = "order_status"),
		@Result(property = "comName", column = "com_name"),
		@Result(property = "comImgurl", column = "com_imgurl")
	//com_name,com_imgurl
	})
	public List<PlatformOrderEntity> getPlatformOrder(String username); 
	
	
	@Select("select uoo.userid,original_order_id, afqxh,dskrq,fje,flx,azt,acpfamc,realname "
			+ "from user_original_order uoo left join user u on uoo.userid =u.userid "
			+ "where uoo.userid =#{userNamne}")
	@Results({@Result(property = "userid" ,column="userid"),
		@Result(property ="ID" ,column="original_order_id"),
		@Result(property = "afqxh" ,column="afqxh"),
		@Result(property = "dskrq" ,column="dskrq"),
		@Result(property = "fje" ,column="fje"),
		@Result(property = "flx" ,column="flx"),
		@Result(property = "azt" ,column="azt"),
		@Result(property = "ahkrkhm",column ="realname"),
		@Result(property="acpfamc",column="acpfamc")})
	public List<OriginalOrderEntity> getOriginalOrder(String userNamne);
	
	@Select("select uoo.userid,original_order_id, afqxh,dskrq,fje,flx,azt,realname "
			+ "from user_original_order uoo left join user u on uoo.userid =u.userid "
			+ "where uoo.userid =#{userNamne}")
	@Results({@Result(property = "userid" ,column="userid"),
		@Result(property ="ID" ,column="original_order_id"),
		@Result(property = "afqxh" ,column="afqxh"),
		@Result(property = "dskrq" ,column="dskrq"),
		@Result(property = "fje" ,column="fje"),
		@Result(property = "flx" ,column="flx")})
	public List<OriginalOrderEntity> getAllOriginalOrder();


	@Select("SELECT original_order_id ,dfkrq,acpfamc,asqbh from user_original_order where userid =#{phone} GROUP BY asqbh")
	public List<OriginalOrderPageOneBean> getOriginalOrderPageOne(String phone);

	

	@Select("SELECT userid,original_order_id,afqxh,dskrq,dfkrq,fje,flx,AZT,dzzrq from user_original_order where userid =#{0} and  asqbh =#{1} and afqxh >0")
	@Results({@Result(property = "userId" ,column="userid"),
		@Result(property ="originalOrderId" ,column="original_order_id"),
		@Result(property = "afqxh" ,column="afqxh"),
		@Result(property = "dzzrq" ,column="afqxh"),
		@Result(property = "dskrq" ,column="dskrq"),
		@Result(property = "fje" ,column="fje"),
		@Result(property = "flx" ,column="flx"),
		@Result(property = "azt" ,column="AZT"),
		@Result(property = "dzzrq",column ="dzzrq")})
	public List<OriginalOrderByStagesRecordBean> findOriginalOrderByStagesRecordBeanbyPhoneAndAsqbh(String phone,
			String asqbh);

}
