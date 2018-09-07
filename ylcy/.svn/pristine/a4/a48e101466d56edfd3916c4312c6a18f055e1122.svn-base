package com.ylch.ylcy.ylcy.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ylch.ylcy.ylcy.common.entitys.AppVersion;
import com.ylch.ylcy.ylcy.common.service.AppUpdateService;

@RestController
@SuppressWarnings("all")
public class AppUpdateController {

	@Autowired
	private AppUpdateService appUpdateService;
	
	@RequestMapping("/checkVersionUpdate")
	public Map updateVsersion(Model m) {
		String resultCode = "1";
		String resultMessage = "";
		Map data = new HashMap();
		
		List<AppVersion> listVersion = appUpdateService.selAppVersionInfo();
		if (!listVersion.isEmpty()) {
			for (AppVersion appVersion : listVersion) {
				if ("ios".equals(appVersion.getSysType())) {
					data.put("IosVersion", appVersion);
				}else {
					data.put("AndroidVersion", appVersion);
				}
			}
		}else {
			resultCode = "0";
			resultMessage = "系统错误!";
		}

		HashMap hmp = new HashMap();
		hmp.put("resultCode", resultCode);
		hmp.put("resultMessage", resultMessage);
		hmp.put("data", data);
		return hmp;
	}
}
