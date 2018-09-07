package com.ylch.ylcy.ylcy.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoForBuyGoods;
import com.ylch.ylcy.ylcy.user.entity.PersionInfoEntity;

@Mapper
public interface UserDao {
	@Insert("insert into user (userid,passwd,phone) VALUE (#{0},#{1},#{2})")
	int insertUser(String userid,String  password,String mobile);
	
	@Update("update user set imgurl = #{1} where userid= #{0}")
	void uploadImage(String userid,String imgurl);
	
	@Select("select userid, phone, passwd from user where userid=#{userid}")
	@Results({ 	@Result(property = "userid", column = "userid"),
				@Result(property = "phone",column = "phone"),
				@Result(property = "passwd", column = "passwd")})
	public UserEntity getUser(String userid);
	
	@Select("select userid from user where userid=#{userid}")
	@Results({ 	@Result(property = "userName", column = "userid")})
	public String verifyUser(String userid);
	
	//@Select("select phone,nickname,realname,idcard from user where userid=#{userid}")
	@Select("select phone,nickname,realname,idcard ,imgurl,point_num "
			+ "from user LEFT JOIN point_total pt on `user`.userid = pt.userid where `user`.userid =#{userid}" )
	@Results({ 	
				@Result(property = "phone", column = "phone"),
				@Result(property = "nickname", column = "nickname"),
				@Result(property = "realname", column = "realname"),
				@Result(property ="idcard",column="idcard"),
				@Result(property ="imgurl",column="imgurl"),
				@Result(property ="pointTotal",column="point_num")})
	public PersionInfoEntity getUserPersionInfo(String userid);
	
	@Update("update user set passwd = #{1} where userid = #{0} ")
	int updateUserPassword(String phone,String password);
	
	@Update("update user set nickname = #{1} where userid = #{0}")
	int changeNickName(String userid,String nickName);
	
	

	@Select("select phone from user where phone =#{phone} ")
	public String  verifyPhoneExist(String phone);

	@Update("update user set phone = #{0},userid =#{0} where userid =#{1}")
	int  updatePhone(String NewPhone,String originalPhone);
	
	@Select("select passwd from user where userid = #{userid}")
	String getPasswordByPhone(String userid);

	@Update("update user set realName = #{1} ,idCard = #{2} where userid =#{0}")
	int  insertRealName(String userid, String realName, String idCard);

	@Select("select userid,phone, passwd from user where userid=#{userid}")
	@Results({ 	@Result(property = "userid", column = "userid"),
				@Result(property = "phone",column = "phone"),
				@Result(property = "passwd", column = "passwd")})
	UserEntity findUserByCurrentUser(String  userid);

	@Select("select realname,nickname, phone, idcard,imgurl from user where userid=#{phone}")
	@Results({ 	@Result(property = "realname", column = "realname"),
				@Result(property = "nickname",column = "nickname"),
				@Result(property = "phone", column = "phone"),
				@Result(property = "idcard",column = "idcard"),
				@Result(property = "imgurl",column = "imgurl")})
	UserInfoEntity getUserInfo(String phone);
	/*private String	  realname;// 真实姓名
	private String	  nickname;//呢称
	private String	  phone;//手机号
	private String	  idcard;//身份证号
	private String imgurl;// 头像信息
	private String access_token;//token
*/	
	@Select("select userid from user")
	List<String> getAllUserid();

	@Select("select count(*) from user where idcard=#{idCard}")
	int IDCardIsExit(String idCard);
	@Select("SELECT userid,realname,phone,mrdz FROM `user` where userid =#{phone}")
	UserInfoForBuyGoods getUserInfoForBuyGoods(String phone);
	

}
