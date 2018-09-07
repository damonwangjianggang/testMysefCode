package com.ylch.ylcy.ylcy.shops.service;

import org.springframework.stereotype.Service;

import com.ylch.ylcy.ylcy.shops.entitys.Goods;

@Service
public interface JFGoodsServerService {

	public void saveGoods(Goods paramGoods);//保存商品
	
	public Goods showGoods(int com_id);//展示商品
}
