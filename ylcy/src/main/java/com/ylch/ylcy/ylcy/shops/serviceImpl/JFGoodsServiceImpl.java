package com.ylch.ylcy.ylcy.shops.serviceImpl;

import com.ylch.ylcy.ylcy.common.entitys.PageBean;
import com.ylch.ylcy.ylcy.common.entitys.RsgContent;
import com.ylch.ylcy.ylcy.shops.dao.DaoUtils;
import com.ylch.ylcy.ylcy.shops.dao.JFGoodsDao;
import com.ylch.ylcy.ylcy.shops.entitys.DataBean;
import com.ylch.ylcy.ylcy.shops.entitys.Goods;
import com.ylch.ylcy.ylcy.shops.entitys.GoodsTicket;
import com.ylch.ylcy.ylcy.shops.entitys.JFRecords;
import com.ylch.ylcy.ylcy.shops.service.JFGoodsService;
import com.ylch.ylcy.ylcy.shops.utils.JFConstant;
import com.ylch.ylcy.ylcy.user.dao.IntegralDao;
import com.ylch.ylcy.ylcy.user.dao.UserDao;
import com.ylch.ylcy.ylcy.user.entity.IntegralEntity;
import com.ylch.ylcy.ylcy.user.entity.PersionInfoEntity;
import com.ylch.ylcy.ylcy.user.entity.PlatformOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PointRecordEntity;
import com.ylch.ylcy.ylcy.user.entity.UserInfoForBuyGoods;
import com.ylch.ylcy.ylcy.user.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class JFGoodsServiceImpl implements JFGoodsService {

	@Autowired
	private JFGoodsDao jfGoodsDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DaoUtils dUtils;
	
	@Autowired
	private IntegralDao integralDao;
	
	@Autowired
	private UserService usService;

	/**
	 * 查询商品带分页
	 */
	public List<Goods> selectGoodsPage(RsgContent rsgContent) {
		PageBean pageBean = new PageBean();
		int pageIndex = rsgContent.getPageIndex();
		int pageSize = rsgContent.getPageSize();
		if (pageIndex == 1)
			pageBean.setBegin(0);
		else {
			pageBean.setBegin((pageIndex - 1) * pageSize + 1);
		}
		pageBean.setEachPageSize(pageSize);
		return jfGoodsDao.selectGoodsPage(pageBean);
	}

	/**
	 * 查询商品
	 */
	public List<Goods> selectGoods() {
		return jfGoodsDao.selectGoods();
	}

	/**
	 * 根据id值查询商品
	 */
	public Goods selectGoodById(RsgContent rsgContent) {
		return jfGoodsDao.selectGoodById(rsgContent);
	}

	/**
	 * 兑换商品
	 */
	public Map buyGoods(RsgContent rsgContent,PersionInfoEntity per) {
		Map map = new HashMap();
		//获取商品详情
		Goods goods = jfGoodsDao.selectGoodById(rsgContent);
		int stock = goods.getStock();//库存
		if (stock > 0) {
			//获取总积分
			IntegralEntity inEntity = integralDao.getIntegralByUserid(rsgContent.getPhone());
			String pointNum = inEntity.getPointNumber();//用户总积分
			String comJf = goods.getCom_jf();//商品积分
			if (Integer.parseInt(pointNum) >= Integer.parseInt(comJf)) {
				//满足兑换条件
				/*积分变动 start*/
				//生成积分变动记录
				PointRecordEntity pointRE = new PointRecordEntity();
				pointRE.setUserid(rsgContent.getPhone());
				pointRE.setRecordId(UUID.randomUUID().toString());
				pointRE.setType("-");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		        pointRE.setTime(df.format(new Date()));
		        pointRE.setReason(JFConstant.JF_DUIHUAN);
		        pointRE.setIntegralNumerical(comJf);
		        pointRE.setRepaymentAmount("");
		        jfGoodsDao.savePointRecord(pointRE);
		        /*积分变动 end*/
		        
				int isTicket = goods.getIsTicket();
				if (isTicket == 1) {//虚拟货物
					/*库存变动 start*/
			        //查询商品卷码
					//GoodsTicket gTicketInto = new GoodsTicket(goods.getCom_id(), JFConstant.TK_YES);
					int com_id = goods.getCom_id();
					String status = JFConstant.TK_YES;
			        GoodsTicket gTicket = jfGoodsDao.selCode(com_id,status);
			        //更新卷码状态(数据库设置触发器,卷码状态改变则库存对应改变)
			        jfGoodsDao.updateCodeStatus(gTicket);
			        /*库存变动 end*/
			        
			        /*生成订单记录 start*/
			        PlatformOrderEntity pfOrderEntity = new PlatformOrderEntity();
			        pfOrderEntity.setUserid(rsgContent.getPhone());
			        pfOrderEntity.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
			        pfOrderEntity.setOrderAddr(gTicket.getCode());
			        pfOrderEntity.setComId(goods.getCom_id());
			        pfOrderEntity.setOrderStatus(JFConstant.TK_NO);//虚拟货物兑换成功默认订单已完成
			        jfGoodsDao.savePfOrderInfo(pfOrderEntity);
			        /*生成订单记录 end*/
			        
			        map.put("order_id", pfOrderEntity.getOrderId());
			        map.put("resultCode", "1");
				}else {//实物商品
					/*库存变动 start*/
			        jfGoodsDao.updateStockNum(goods.getStock()-1,goods.getCom_id());
			        /*库存变动 end*/
			        /*生成订单记录 start*/
			        UserInfoForBuyGoods userInfoForBuyGoods = usService.getUserInfoForBuyGoods(rsgContent.getPhone());
			        PlatformOrderEntity pfOrderEntity = new PlatformOrderEntity();
			        pfOrderEntity.setUserid(rsgContent.getPhone());
			        pfOrderEntity.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
			        pfOrderEntity.setOrderAddr(userInfoForBuyGoods.getMrdz());
			        pfOrderEntity.setComId(goods.getCom_id());
			        pfOrderEntity.setOrderStatus(JFConstant.TK_NO);
			        jfGoodsDao.savePfOrderInfo(pfOrderEntity);
			        /*生成订单记录 end*/
			        
			        map.put("order_id", pfOrderEntity.getOrderId());
			        map.put("resultCode", "1");
				}
			}else {
				map.put("resultCode", "0");
				map.put("resultMessage", "用户积分不足!");
			}
			
		}else {
			map.put("resultCode", "0");
			map.put("resultMessage", "商品库存不足!");
		}
		return map;
	}
	
	/**
	 * 查询商品详细信息(卷码/地址)
	 */
	public Map showTicketInfo(RsgContent rsgContent) {
		Map map = new HashMap();
		//根据订单id 查询订单
		PlatformOrderEntity pfOrderEntity = jfGoodsDao.selPfOrderInfoById(rsgContent.getOrder_id());
		rsgContent.setCom_id(pfOrderEntity.getComId());
		//获取商品详情
		Goods goods = jfGoodsDao.selectGoodById(rsgContent);
		int isTicket = goods.getIsTicket();
		if (isTicket == 1) {//虚拟货物
			String url = jfGoodsDao.selCompanyUrl(pfOrderEntity.getComId());
			map.put("order_addr", pfOrderEntity.getOrderAddr());
			map.put("url", url);
			map.put("com_id", pfOrderEntity.getComId());
			map.put("resultCode", "1");
		}else {//实物商品
			map.put("order_addr", pfOrderEntity.getOrderAddr());
			map.put("com_id", pfOrderEntity.getComId());
			map.put("resultCode", "1");
		}
		
		return map;	
	}
}