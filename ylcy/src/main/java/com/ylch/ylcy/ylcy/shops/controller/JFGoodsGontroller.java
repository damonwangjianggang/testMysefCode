package com.ylch.ylcy.ylcy.shops.controller;

import com.ylch.ylcy.ylcy.common.entitys.RsgContent;
import com.ylch.ylcy.ylcy.shops.entitys.Goods;
import com.ylch.ylcy.ylcy.shops.service.JFGoodsService;
import com.ylch.ylcy.ylcy.user.bean.ResponseBean;
import com.ylch.ylcy.ylcy.user.entity.PersionInfoEntity;
import com.ylch.ylcy.ylcy.user.service.UserService;
import com.ylch.ylcy.ylcy.user.task.IntegralComputationTaskForSpringMVC;
import com.ylch.ylcy.ylcy.user.util.JWTUtil;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class JFGoodsGontroller {

	@Autowired
	private JFGoodsService jfService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private UserService userService;
	

	/**
	 * 商品首页
	 * 
	 * @param request
	 * @param rsgContent
	 * @return
	 */
	@RequestMapping({ "/integralMall" })
	public Object integralMall(HttpServletRequest request, @RequestBody RsgContent rsgContent) {
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, JWTUtil.getUsername(access_token), userService.getPasswordByPhone(JWTUtil.getUsername(access_token)))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		
		String resultCode = "1";
		String resultMessage = "";
		Map data = new HashMap();

		List listGoods = jfService.selectGoodsPage(rsgContent);
		data.put("listGoods", listGoods);

		HashMap hmp = new HashMap();
		hmp.put("resultCode", resultCode);
		hmp.put("resultMessage", resultMessage);
		hmp.put("data", data);
		return hmp;
	}

	/**
	 * 商品详情
	 * 
	 * @param request
	 * @param rsgContent
	 * @return
	 */
	@RequestMapping({ "/goodDetail" })
	public Object goodDetail(HttpServletRequest request, @RequestBody RsgContent rsgContent) {
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, JWTUtil.getUsername(access_token), userService.getPasswordByPhone(JWTUtil.getUsername(access_token)))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		
		String resultCode = "1";
		String resultMessage = "";
		Map data = new HashMap();

		Goods good = jfService.selectGoodById(rsgContent);

		data.put("good", good);
		data.put("exchangeStatue", Boolean.valueOf(true));

		HashMap hmp = new HashMap();
		hmp.put("resultCode", resultCode);
		hmp.put("resultMessage", resultMessage);
		hmp.put("data", data);
		return hmp;
	}

	/**
	 * 兑换商品
	 * 
	 * @param rsgContent
	 * @return
	 */
	@RequestMapping({ "/buyGood" })
	public Object buyGood(HttpServletRequest request,@RequestBody RsgContent rsgContent) {
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, JWTUtil.getUsername(access_token), userService.getPasswordByPhone(JWTUtil.getUsername(access_token)))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		
		String resultCode = "1";
		String resultMessage = "";
		Map data = new HashMap();

		String phone = rsgContent.getPhone();
		PersionInfoEntity persionInfoEntity = uService.getUserPersionInfo(phone);
		if (persionInfoEntity != null) {
			Map map = jfService.buyGoods(rsgContent,persionInfoEntity);
			resultCode = (String) map.get("resultCode");
			if ("1".equals(resultCode)) {
				data.put("order_id", map.get("order_id"));
			}else {
				resultMessage = (String) map.get("resultMessage");
			}
		}else {
			resultCode = "0";
			resultMessage = "参数错误!";
		}

		HashMap hmp = new HashMap();
		hmp.put("resultCode", resultCode);
		hmp.put("resultMessage", resultMessage);
		hmp.put("data", data);
		return hmp;
	}
	
	/**
	 * 商品信息展示页
	 */
	@RequestMapping("/showTicket")
	public Object showTicket(HttpServletRequest request,@RequestBody RsgContent rsgContent) {
		String access_token=request.getHeader("access_token");
		if(!JWTUtil.verify(access_token, JWTUtil.getUsername(access_token), userService.getPasswordByPhone(JWTUtil.getUsername(access_token)))) {
				return new ResponseBean(null, "07", "登陆过期，请重新登陆");
			}
		String resultCode = "1";
		String resultMessage = "";
		Map data = new HashMap();
		
		String phone = rsgContent.getPhone();
		PersionInfoEntity persionInfoEntity = uService.getUserPersionInfo(phone);
		if (persionInfoEntity != null) {
			Map map = jfService.showTicketInfo(rsgContent);
			resultCode = (String) map.get("resultCode");
			if ("1".equals(resultCode)) {
				Goods good = jfService.selectGoodById(rsgContent);
				data.put("goods", good);
				data.put("order_addr", map.get("order_addr"));
				data.put("url", map.get("url"));
			}else {
				resultMessage = (String) map.get("resultMessage");
			}
		}else {
			resultCode = "0";
			resultMessage = "参数错误!";
		}
		
		HashMap hmp = new HashMap();
		hmp.put("resultCode", resultCode);
		hmp.put("resultMessage", resultMessage);
		hmp.put("data", data);
		return hmp;
	}
}