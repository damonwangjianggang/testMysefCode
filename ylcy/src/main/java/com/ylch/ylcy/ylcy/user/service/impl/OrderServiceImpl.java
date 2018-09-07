package com.ylch.ylcy.ylcy.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylch.ylcy.ylcy.user.bean.OriginalOrderByStagesRecordBean;
import com.ylch.ylcy.ylcy.user.bean.OriginalOrderPageOneBean;
import com.ylch.ylcy.ylcy.user.bean.OriginalOrderPageTotalInfoBean;
import com.ylch.ylcy.ylcy.user.bean.OriginalOrderPageTwoBean;
import com.ylch.ylcy.ylcy.user.dao.OrderDao;
import com.ylch.ylcy.ylcy.user.entity.OriginalOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PlatformOrderEntity;
import com.ylch.ylcy.ylcy.user.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Override
	public List<PlatformOrderEntity> getPlatformOrder(String userName) {
		return orderDao.getPlatformOrder(userName);
	}
	@Override
	public List<OriginalOrderEntity> getOriginalOrder(String userName) {

		return orderDao.getOriginalOrder(userName);
	}
	@Override
	public List<OriginalOrderPageOneBean> getOriginalOrderPageOne(String phone) {
		List<OriginalOrderPageOneBean> OriginalOrderPageOneBeanList=orderDao.getOriginalOrderPageOne(phone);
		for (OriginalOrderPageOneBean originalOrderPageOneBean : OriginalOrderPageOneBeanList) {
			originalOrderPageOneBean.setDfkrq(originalOrderPageOneBean.getDfkrq().substring(0, 9));
		}
		return OriginalOrderPageOneBeanList;
	}
	@Override
	public OriginalOrderPageTwoBean getOriginalOrderPageTwo(String phone,String asqbh) {
		OriginalOrderPageTwoBean orderPageTwoBean = new OriginalOrderPageTwoBean();
		//
		List<OriginalOrderByStagesRecordBean>  originalOrderByStagesRecordBeanList =orderDao.findOriginalOrderByStagesRecordBeanbyPhoneAndAsqbh(phone,asqbh);
		for (OriginalOrderByStagesRecordBean originalOrderByStagesRecordBean : originalOrderByStagesRecordBeanList) {
			if(originalOrderByStagesRecordBean.getDskrq()!=null) {
				originalOrderByStagesRecordBean.setDskrq(originalOrderByStagesRecordBean.getDskrq().substring(0, 9));
			}
			if(originalOrderByStagesRecordBean.getDzzrq()!=null) {
				originalOrderByStagesRecordBean.setDzzrq(originalOrderByStagesRecordBean.getDzzrq().substring(0, 9));
			}
			originalOrderByStagesRecordBean.setFje(String.valueOf(Float.valueOf(originalOrderByStagesRecordBean.getFje())+Float.valueOf(originalOrderByStagesRecordBean.getFlx())));
			originalOrderByStagesRecordBean.setAfqxh(originalOrderByStagesRecordBean.getAfqxh()+"/"+originalOrderByStagesRecordBeanList.size());
		}
		//
		OriginalOrderPageTotalInfoBean originalOrderPageTotalInfoBean = getOriginalOrderPageTotalInfoBean(phone,asqbh);
		orderPageTwoBean.setOriginalOrderByStagesRecordBeanList(originalOrderByStagesRecordBeanList);
		orderPageTwoBean.setOriginalOrderPageTotalInfoBean(originalOrderPageTotalInfoBean);
		return orderPageTwoBean;
	}
	//
	public OriginalOrderPageTotalInfoBean getOriginalOrderPageTotalInfoBean(String phone,String asqbh) {
		OriginalOrderPageTotalInfoBean originalOrderPageTotalInfoBean = new OriginalOrderPageTotalInfoBean();
		List<OriginalOrderByStagesRecordBean>  originalOrderByStagesRecordBeanList=orderDao.findOriginalOrderByStagesRecordBeanbyPhoneAndAsqbh(phone,asqbh);
		float  totalFje=0;//总本金
		float totalFlx=0;//总利息
		float finishFje=0;//已还本金
		float finishFlx=0;//已还利息
		for (OriginalOrderByStagesRecordBean originalOrderByStagesRecordBean : originalOrderByStagesRecordBeanList) {
			totalFje =totalFje+Float.valueOf(originalOrderByStagesRecordBean.getFje());
			totalFlx =totalFlx+Float.valueOf(originalOrderByStagesRecordBean.getFlx());
			if("3".equals(originalOrderByStagesRecordBean.getAzt())) {
				finishFje =finishFje+Float.valueOf(originalOrderByStagesRecordBean.getFje());
				finishFlx =finishFlx+Float.valueOf(originalOrderByStagesRecordBean.getFlx());
			}
		}
		originalOrderPageTotalInfoBean.setServiceCharge(String.valueOf(totalFlx));//总利息，手续费
		originalOrderPageTotalInfoBean.setTotalInstalment(String.valueOf(totalFje+totalFlx));//总额含手续费
		originalOrderPageTotalInfoBean.setStagingSurplusMoney(String.valueOf(totalFje+totalFlx-finishFje-finishFlx));//剩余所需还款
		return originalOrderPageTotalInfoBean;
	}
}
