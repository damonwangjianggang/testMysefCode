package com.ylch.ylcy.ylcy.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ylch.ylcy.ylcy.user.bean.OriginalOrderPageOneBean;
import com.ylch.ylcy.ylcy.user.bean.OriginalOrderPageTwoBean;
import com.ylch.ylcy.ylcy.user.entity.OriginalOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PlatformOrderEntity;

@Service
public interface OrderService {
	public List<PlatformOrderEntity> getPlatformOrder(String userName);
	public List<OriginalOrderEntity> getOriginalOrder(String userName);
	public List<OriginalOrderPageOneBean> getOriginalOrderPageOne(String phone);
	OriginalOrderPageTwoBean getOriginalOrderPageTwo(String phone, String asqbh);
}
