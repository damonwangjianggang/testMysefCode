package com.ylch.ylcy.ylcy.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ylch.ylcy.ylcy.user.entity.OriginalOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PointRecordEntity;
import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.entity.UserIDAndIDcardEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoEntity;

@Mapper
public interface TaskDao {
	@Select("select userid,idcard from user")
	@Results({@Result(property="userId",column="userId"),
			  @Result(property="idCard",column="idcard")})
	public List<UserIDAndIDcardEntity> getIDCard();
	
	@Select("SELECT asqbh FROM lb_apply_main WHERE AZJHM =#{idCard}")
	public List<String> getAsqbh(String idCard);
	
	@Select("SELECT * from user_original_order")
	@Results({@Result(property="userid",column="ID"),
			@Result(property="recordId",column="afqxh"),
			@Result(property="time",column="DSKRQ")})
	public List<PointRecordEntity> getUserOriginalOrderForPointRecord(String asqbh);

	@Select("SELECT repay.ID ,repay.afqxh , repay.dskrq, repay.fje, repay.flx, repay.AZT,repay.DFKRQ,main.acpfamc,repay.asqbh,repay.dzzrq from lb_repay_plan repay LEFT JOIN lb_apply_main main "
			+ "on repay.asqbh =main.asqbh where repay.DFKRQ !='' and repay.ASQBH = #{asqbh}")
	@Results({@Result(property="ID",column="ID"),
			@Result(property="afqxh",column="afqxh"),
			@Result(property="dskrq",column="dskrq"),
			@Result(property="fje",column="fje"),
			@Result(property="flx",column="flx"),
			@Result(property="azt",column="azt"),
			@Result(property="acpfamc",column="acpfamc"),
			@Result(property="dfkrq",column="dfkrq"),
			@Result(property="asqbh",column="asqbh"),
			@Result(property="dzzrq",column="dzzrq")})
	public List<OriginalOrderEntity> getOriginalOrderByAsqbh(String asqbh);
	
	@Select("select count(*) from user_original_order where original_order_id =#{id}")
	public int  orderIDisExit(String id);
//@Insert("insert into users(name,age) values(#{name},#{age})")
	@Insert("insert into user_original_order "
			+ "(userid,original_order_id,afqxh,dskrq,fje,flx,azt,acpfamc,dfkrq,asqbh,dzzrq) "
	+ "values(#{userid},#{ID},#{afqxh},#{dskrq},#{fje},#{flx},#{azt},#{acpfamc},#{dfkrq},#{asqbh},#{dzzrq})")
	public int insertIntoOriginalOrderFromLbRepayPlan(OriginalOrderEntity originalOrderEntity);
	
	@Select("SELECT * from user_original_order")
	@Results({@Result(property="userid",column="userid"),
			@Result(property="ID",column="original_order_id"),
			@Result(property="afqxh",column="afqxh"),
			@Result(property="dskrq",column="dskrq"),
			@Result(property="fje",column="fje"),
			@Result(property="flx",column="flx"),
			@Result(property="azt",column="AZT")})
	public List<OriginalOrderEntity> getAllOriginalOrder();

	@Select("select count(*) from point_record where record_id =#{id}")
	public int orderIDisExitInPonintRecord(String id);
	
	@Insert("INSERT into point_record (userid,record_id,type,time,reason,integral_numerical,repayment_amount) "
			                + "VALUE(#{userid},#{recordId},#{type},#{time},#{reason},#{integralNumerical},#{repaymentAmount}) ")
	public int  insertIntoPointRecordFromOriginalOrder(PointRecordEntity pointRecordEntity);
	
	@Select("select lal.asjhm,lal.azjhm,lal.AKHXM from lb_apply_main lam "
			+ "left join lb_apply_lessee_info lal  on lam.asqbh =lal.asqbh "
			+ "where lam.dfkrq !='' and lal.azjhm !='' and lam.ASFFDR=0 GROUP BY lal.asjhm")
	@Results({@Result(property="userid",column="asjhm"),
		      @Result(property="phone", column="asjhm"),
		      @Result(property="azjhm", column="azjhm"),
		      @Result(property="akhxm", column="AKHXM")})
	public List<UserEntity> getAllUserFromlbApplyMain();

	@Select("select count(*) from user where idcard=#{azjhm}")
	public int isUserExitInUser(String azjhm);
	
	@Insert("insert into user (userid,realname,phone,idcard) VALUE(#{userid},#{akhxm},#{phone},#{azjhm})")
	public int insertIntoUserFromLbApplyMain(UserEntity userEntity);
	
	@Select("select * from user where idcard = #{azjhm}")
	@Results({@Result(property="userid",column="userid"),
			  @Result(property="idcard",column="azjhm")})
	public UserEntity getUserFromUser(String azjhm);

	@Select("SELECT * from user_original_order where original_order_id=#{id}")
	@Results({@Result(property="userid",column="userid"),
			@Result(property="ID",column="original_order_id"),
			@Result(property="afqxh",column="afqxh"),
			@Result(property="dskrq",column="dskrq"),
			@Result(property="fje",column="fje"),
			@Result(property="flx",column="flx"),
			@Result(property="azt",column="AZT")})
	public OriginalOrderEntity findOriginalOrderByID(String id);

	@Update("update user_original_order set AZT =#{azt} ,dskrq=#{dskrq} where original_order_id=#{ID}")
	public int updateOriginalOrderAZT(OriginalOrderEntity originalOrderEntity);


}
