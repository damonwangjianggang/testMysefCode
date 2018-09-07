package com.ylch.ylcy.ylcy.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ylch.ylcy.ylcy.user.entity.IntegralDetailEntity;
import com.ylch.ylcy.ylcy.user.entity.IntegralEntity;
import com.ylch.ylcy.ylcy.user.entity.PointRecordEntity;

@Service
public interface IntegralService {
	public IntegralEntity getIntegralByUserid(String userid);
	public List<PointRecordEntity> getIntegralRecordDetailByUserid(String userid);
	public int insertOrUpdateIntegral(String userid, String totalIntegral);

}
