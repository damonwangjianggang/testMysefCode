package com.ylch.ylcy.ylcy.shops.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ylch.ylcy.ylcy.shops.entitys.DataBean;

@Mapper
public abstract interface DaoUtils {
	/**
	 * 查询数据字典意思
	 * @param paramDataBean
	 * @return
	 */
	@Select({ "SELECT dic_name FROM ifs_data WHERE bid=#{bid} and dic_num=#{dic_num}" })
	@Results({ @Result(property = "dic_name", column = "dic_name") })
	public String selCMean(DataBean paramDataBean);
}