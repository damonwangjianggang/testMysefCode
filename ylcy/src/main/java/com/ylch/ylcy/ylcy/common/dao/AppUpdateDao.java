package com.ylch.ylcy.ylcy.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ylch.ylcy.ylcy.common.entitys.AppVersion;

@Mapper
public interface AppUpdateDao {

	@Select({
			"SELECT * FROM app_version" })
	@Results({
			@Result(property = "sysType",column = "sys_type"),
			@Result(property = "appId",column = "app_id"),
			@Result(property = "appName",column = "app_name"),
			@Result(property = "versionCode",column = "versionCode"),
			@Result(property = "versionName",column = "versionName"),
			@Result(property = "downLoadURL",column = "downLoadURL"),
			@Result(property = "updateId",column = "update_id"),
			@Result(property = "updateTime",column = "update_time"),
			@Result(property = "updateInstall",column = "update_install"),
			@Result(property = "updateDetail",column = "update_detail")})
			
	public List<AppVersion> selAppVersionInfo();
}
