package com.ylch.ylcy.ylcy.user.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ylch.ylcy.ylcy.user.bean.ResponseBean;
import com.ylch.ylcy.ylcy.user.entity.OriginalOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.PointRecordEntity;
import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.entity.UserIDAndIDcardEntity;
import com.ylch.ylcy.ylcy.user.service.IntegralService;
import com.ylch.ylcy.ylcy.user.service.TaskService;
import com.ylch.ylcy.ylcy.user.service.UserService;

@Component
public class IntegralComputationTaskForSpringTask {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private IntegralService integralService;
	// select lal.asjhm ,lal.asjhm,lal.azjhm from lb_apply_main lam left join lb_apply_lessee_info lal on lam.asqbh =lal.asqbh where lam.dfkrq is not null and lam.ASFFDR=0 GROUP BY lal.asjhm
	//insert into user (userid,phone,idcard)value( lal.asjhm,lal.asjhm,lal.azjhm);
	//select  count(*) from user where userid =#{userid}
	//用户更新任务
	//@Scheduled(cron="0 0/5 0 * * ?")//每隔1秒隔行一次
	//@Scheduled(fixedRate = 60000)
	@Scheduled(cron = "0 0 21 * * ?")
	public ResponseBean updateUser() {
		System.out.println("//用户更新任务+@Scheduled(cron=\"20  22 * * ?\")// +updateUser()");
		//拿到所有有限用户
		List<UserEntity> userList = taskService.getAllUserFromlbApplyMain();
		//遍历插入user表
		for (UserEntity userEntity : userList) {
			if(!taskService.isUserExitInUser(userEntity)) {
				taskService.insertIntoUserFromLbApplyMain(userEntity);
			}
		}
		return new ResponseBean(null,"1","success");
	}
	
	
	//每隔1秒隔行一次 //获取用户还款记录，并插入到user_original_order记录表中，
	@Scheduled(cron = "0 20 21 * * ?")
	public ResponseBean OriginalOrderUpdate() {
		System.out.println("//每隔1秒隔行一次 //获取用户还款记录，并插入到user_original_order记录表中，+@Scheduled(cron=\"0 40 14 * * ?\")+OriginalOrderUpdate()");
		List<UserIDAndIDcardEntity> idCardList = taskService.getIdCard();
		// 遍历整个用户表,拿到每个用户的身份证信息，
		for (UserIDAndIDcardEntity idCard : idCardList) {
			//根据用户身份信息，拿到所有的申请编号
			List<String> asqbhList = taskService.getAsqbh(idCard.getIdCard());
			//拿到用户的所有申请编号信息，
			for (String asqbh : asqbhList) {
				//获取每个申请编号下的用户的还款信息
				List<OriginalOrderEntity> originalOrderList = taskService.getOriginalOrderByAsqbh(asqbh);
				//遍历所有的还款记录，加上用户的id信息
				for (OriginalOrderEntity originalOrderEntity : originalOrderList) {
					originalOrderEntity.setUserid(idCard.getUserId());
					//	判断此记录ID是否存在
					if(!taskService.orderIDisExit(originalOrderEntity)) {
						   taskService.insertIntoOriginalOrderFromLbRepayPlan(originalOrderEntity);
					   }else if(taskService.isNeedUpdateAZT(originalOrderEntity)) {
						   taskService.updateOriginalOrderAZT(originalOrderEntity);
						   
					   }
				}
			}
			
		}
		return new ResponseBean(null,"1","1");
	}
	//将用户原始订单数据换算到积分表记录里面
	@Scheduled(cron = "0 40 21 * * ?")
	public ResponseBean integralScaler() {
		System.out.println("//将用户原始订单数据换算到积分表记录里面+integralScaler()+@Scheduled(cron=\"0 0 15 * * ?\")");
		//拿到所有用户的还款记录
		List<OriginalOrderEntity> originalOrderList =taskService.getAllOriginalOrder();
		for (OriginalOrderEntity originalOrderEntity : originalOrderList) {
			if(!taskService.orderIDisExitInPonintRecord(originalOrderEntity)) {
				taskService.insertIntoPointRecordFromOriginalOrder(originalOrderEntity);
			}
		}
		return new ResponseBean(null,"1","success");
	}
	
	//总积分计算任务
	@Scheduled(cron ="0 0 22 * * ?")
	public ResponseBean totalIntegralCalculate() {
		System.out.println("totalIntegralCalculate//总积分计算任务@Scheduled(cron =\"0 10 15 * * ?\")");
		List<String> useridList = userService.getAllUserid();
		for (String userid : useridList) {
			int  totalIntegral = 0;
			List<PointRecordEntity>  pointRecordEntityList = integralService.getIntegralRecordDetailByUserid(userid);
			for (PointRecordEntity pointRecordEntity : pointRecordEntityList) {
				if("+".equals(pointRecordEntity.getType())) {
					totalIntegral=totalIntegral+Integer.parseInt(pointRecordEntity.getIntegralNumerical());
				}else {
					totalIntegral=totalIntegral-Integer.parseInt(pointRecordEntity.getIntegralNumerical());
				}
			}
			integralService.insertOrUpdateIntegral(userid,String.valueOf(totalIntegral));
 		}
		return null;
	}
}
