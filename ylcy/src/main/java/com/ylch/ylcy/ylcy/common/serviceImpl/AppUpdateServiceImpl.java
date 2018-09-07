package com.ylch.ylcy.ylcy.common.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylch.ylcy.ylcy.common.dao.AppUpdateDao;
import com.ylch.ylcy.ylcy.common.entitys.AppVersion;
import com.ylch.ylcy.ylcy.common.service.AppUpdateService;

@Service
public class AppUpdateServiceImpl implements AppUpdateService {

	@Autowired
	private AppUpdateDao appUpdateDao;
	
	@Override
	public List<AppVersion> selAppVersionInfo() {
		
		return appUpdateDao.selAppVersionInfo();
	}

}
