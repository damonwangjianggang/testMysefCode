package com.ylch.ylcy.ylcy.shops.service;

import com.ylch.ylcy.ylcy.common.entitys.RsgContent;
import com.ylch.ylcy.ylcy.shops.entitys.Goods;
import com.ylch.ylcy.ylcy.shops.entitys.JFRecords;
import com.ylch.ylcy.ylcy.user.entity.PersionInfoEntity;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public abstract interface JFGoodsService {
	public List<Goods> selectGoodsPage(RsgContent paramRsgContent);//查询商品带分页

	public List<Goods> selectGoods();//查询商品

	public Goods selectGoodById(RsgContent paramRsgContent);//根据id值查询商品
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)

	public Map buyGoods(RsgContent paramRsgContent,PersionInfoEntity persionInfoEntity);//兑换商品
	
	public Map showTicketInfo(RsgContent rsgContent);//展示商品详细信息(卷码/地址)
}