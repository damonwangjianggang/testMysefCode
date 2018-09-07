package com.ylch.ylcy.ylcy.home.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ylch.ylcy.ylcy.home.utils.FileUpload;

/**
 * <p>
 * Title: NewsImg_upload
 * </p>
 * <p>
 * Description: 图片上传类
 * </p>
 * 
 * @author AntChenxi
 * @date 2018年6月1日
 */
@RestController
public class NewsImg_upload {
	
	@Value("${internetIP}")
	private String internetIP;

	/**
	 * 单图片上传方法
	 * 
	 * @param request
	 * @param uploadfile
	 * @return json串，path - 服务器上面的地址；isSuccess - true
	 */
	@RequestMapping("/news/imgupload")
	public @ResponseBody Map<String, Object> saveImg(HttpServletRequest request,
			@RequestParam MultipartFile uploadfile) {

		/*InetAddress iaAddress = null;
		String localhost = null;
		String localip = null;
		try {
			iaAddress = iaAddress.getLocalHost();

			localhost = iaAddress.getHostName();
			localip = iaAddress.getHostAddress();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		String hosturl = "http://" + internetIP + ":" + request.getServerPort();
		String filename = UUID.randomUUID() + "." + uploadfile.getOriginalFilename().split("\\.")[1];
		String path = hosturl + "/ylcy/uploadfile/newsImg/" + filename;
		String realpath = request.getSession().getServletContext().getRealPath("/") + "/uploadfile/newsImg/" + filename;
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
