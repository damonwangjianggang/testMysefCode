package com.ylch.ylcy.ylcy.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylch.ylcy.ylcy.user.dao.TaskDao;
import com.ylch.ylcy.ylcy.user.entity.OriginalOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PointRecordEntity;
import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.entity.UserIDAndIDcardEntity;
import com.ylch.ylcy.ylcy.user.service.TaskService;
import com.ylch.ylcy.ylcy.user.service.UserService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDao taskDao;

	@Override
	public List<UserIDAndIDcardEntity> getIdCard() {
		// TODO Auto-generated method stub
		return taskDao.getIDCard();
	}

	@Override
	public List<String> getAsqbh(String idCard) {
		// TODO Auto-generated method stub
		return taskDao.getAsqbh(idCard);
	}

	@Override
	public List<OriginalOrderEntity> getOriginalOrderByAsqbh(String asqbh) {
		// TODO Auto-generated method stub
		return taskDao.getOriginalOrderByAsqbh(asqbh);
	}

	@Override
	public boolean orderIDisExit(OriginalOrderEntity originalOrderEntity) {
		int orderID =taskDao.orderIDisExit(originalOrderEntity.getID());
		if(orderID>0) {
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public int insertIntoOriginalOrderFromLbRepayPlan(OriginalOrderEntity originalOrderEntity) {
			return taskDao.insertIntoOriginalOrderFromLbRepayPlan(originalOrderEntity);
	}

	@Override
	public List<OriginalOrderEntity> getAllOriginalOrder() {
		return taskDao.getAllOriginalOrder();		
	}

	@Override
	public boolean orderIDisExitInPonintRecord(OriginalOrderEntity originalOrderEntity) {
			int a=	taskDao.orderIDisExitInPonintRecord(originalOrderEntity.getID());
			if(a>0) {
				return true;
			}else {
				return false;			
			}
	}

	@Override
	public int  insertIntoPointRecordFromOriginalOrder(OriginalOrderEntity originalOrderEntity) {
		PointRecordEntity pointRecordEntity = new PointRecordEntity();
		//得到用户所还本金 fje 
		String fje = originalOrderEntity.getFje();
		String flx = originalOrderEntity.getFlx();
		double repaymentAmount=Double.parseDouble(fje)+Double.parseDouble(flx);
		//fje 字段放入还款总额
		//还款总额的千分之五为用户所得积分
		int integralNumerical=(int)(repaymentAmount*0.005);
		pointRecordEntity.setUserid(originalOrderEntity.getUserid());
		pointRecordEntity.setRecordId(originalOrderEntity.getID());
		pointRecordEntity.setType("+");
		pointRecordEntity.setTime(originalOrderEntity.getDskrq());
		pointRecordEntity.setReason("还款");
		pointRecordEntity.setIntegralNumerical(String.valueOf(integralNumerical));
		pointRecordEntity.setRepaymentAmount(String.valueOf((int)repaymentAmount));
		return taskDao.insertIntoPointRecordFromOriginalOrder(pointRecordEntity);
		
	}

	@Override
	public List<UserEntity> getAllUserFromlbApplyMain() {
		return taskDao.getAllUserFromlbApplyMain();
	}

	@Override
	public boolean isUserExitInUser(UserEntity userEntity) {
			int userCount = taskDao.isUserExitInUser(userEntity.getAzjhm());
			if(userCount>0) {
				return true;
			}else {
				return false;	
			}
	}

	@Override
	public int  insertIntoUserFromLbApplyMain(UserEntity userEntity) {
		return taskDao.insertIntoUserFromLbApplyMain(userEntity);
	}

	@Override
	public boolean isNeedUpdateAZT(OriginalOrderEntity originalOrderEntity) {
		if(originalOrderEntity.getAzt().equals(taskDao.findOriginalOrderByID(originalOrderEntity.getID()).getAzt())) {
			return false;
		}else {
			return true;			
		}
	}

	@Override
	public int  updateOriginalOrderAZT(OriginalOrderEntity originalOrderEntity) {
		return taskDao.updateOriginalOrderAZT(originalOrderEntity);
	}


}
