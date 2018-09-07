package com.ylch.ylcy.ylcy.shops.controller;

import com.ylch.ylcy.ylcy.common.entitys.RsgContent;
import com.ylch.ylcy.ylcy.shops.entitys.Goods;
import com.ylch.ylcy.ylcy.shops.service.JFGoodsServerService;
import com.ylch.ylcy.ylcy.shops.service.JFGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JFToUpload {

	@Autowired
	private JFGoodsService jfService;
	
	@Autowired
	private JFGoodsServerService jfServerService;

	/**
	 * 商品图片上传
	 * 
	 * @return
	 */
	@RequestMapping({ "/goodImgUpload" })
	public String imgUpload() {
		return "pro_update";
	}

	/**
	 * 保存商品信息
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping({ "/saveGoods" })
	public String saveGoods(Goods goods) {
		jfServerService.saveGoods(goods);
		return "pro_update";
	}

	/**
	 * 展示商品列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/showGoods" })
	public String showGoods(@RequestParam("com_id")int com_id,Model m) {
		Goods goods = jfServerService.showGoods(com_id);
		m.addAttribute("goods",goods);
		return "showgoods";
	}
	
	/**
	 * 向app返回商品详情url
	 */
	@RequestMapping("/showGoodsDetail")
	public String showGoodsDetail(@RequestParam("com_id")int com_id,Model m) {
		RsgContent rsgContent = new RsgContent();
		rsgContent.setCom_id(com_id);
		Goods goods = jfService.selectGoodById(rsgContent);
		m.addAttribute("goods", goods);
		return "showgoodsDetailForApp";
	}
}