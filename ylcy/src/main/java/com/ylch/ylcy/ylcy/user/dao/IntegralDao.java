package com.ylch.ylcy.ylcy.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ylch.ylcy.ylcy.user.entity.IntegralDetailEntity;
import com.ylch.ylcy.ylcy.user.entity.IntegralEntity;
import com.ylch.ylcy.ylcy.user.entity.PointRecordEntity;

@Mapper
public interface IntegralDao {
	/**
	 * get total IntegralEntity
	 * 
	 * @param userid
	 * @return
	 */
	@Select("SELECT * from point_total where userid = #{userid}")
	@Results({ @Result(property = "userid", column = "userid"),
			@Result(property = "pointNumber", column = "point_num") })
	public IntegralEntity getIntegralByUserid(String userid);

	/*
	 * private String userName; private String password; private String name;
	 * private String role; private String permission;
	 */
	/*
	 * private String userid; private String recordId; private String type; private
	 * String time; private String reason;
	 */
	@Select("SELECT * from point_record where userid = #{userid}")
	@Results({ @Result(property = "userid", column = "userid"),
		       @Result(property = "recordId", column = "record_id"),
			   @Result(property = "type", column = "type"),
			   @Result(property = "time", column = "time"), 
			   @Result(property = "reason", column = "reason"),
			   @Result(property = "repaymentAmount", column = "repayment_amount"),
			   @Result(property = "integralNumerical", column = "integral_numerical")})
	public List<PointRecordEntity> getIntegralRecordDetailByUserid(String userid);
	
	@Select("select count(*) from point_total where userid = #{userid}")
	public int getUserIdFromPointRecord(String userid);
	//UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
	@Update("update point_total set point_num =#{1} where userid =#{0}")
	public int updateUserTotailIntegral(String userid, String totalIntegral);
	//更新用户总分信息
	@Insert("insert into point_total (userid,point_num)value(#{0},#{1})")
	public int insertUserTotailIntegral(String userid, String totalIntegral);

}
