package com.ylch.ylcy.ylcy.shops.controller;

import com.ylch.ylcy.ylcy.home.utils.FileUpload;
import com.ylch.ylcy.ylcy.shops.service.JFGoodsService;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ControllerUtils {

	@Autowired
	private JFGoodsService jfService;
	
	@Value("${internetIP}")
	private String internetIP;

	@RequestMapping({ "/goods/imgUpload" })
	@ResponseBody
	public Map<String, Object> upload(@RequestParam MultipartFile uploadfile, HttpServletRequest request) {

		String hosturl = "http://" + internetIP + ":" + request.getServerPort();
		String filename = UUID.randomUUID() + "." + uploadfile.getOriginalFilename().split("\\.")[1];
		String path = hosturl + "/ylcy/uploadfile/goodsImg/" + filename;
		String realpath = request.getSession().getServletContext().getRealPath("/") + "/uploadfile/goodsImg/" + filename;
		Map<String, Object> ret = new HashMap<>();
		try {
			FileUpload.writeToLocal(realpath, uploadfile.getInputStream());
			ret.put("f", path);
			ret.put("isSuccess", true);
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
		}	
		ret.put("f", path);
		ret.put("isSuccess", false);
		return ret;
	}
}