package com.ylch.ylcy.ylcy.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ylch.ylcy.ylcy.user.entity.OriginalOrderEntity;
import com.ylch.ylcy.ylcy.user.entity.UserEntity;
import com.ylch.ylcy.ylcy.user.entity.UserIDAndIDcardEntity;

@Service
public interface TaskService {
	List<UserIDAndIDcardEntity> getIdCard();
	List<String> getAsqbh(String idCard);
	List<OriginalOrderEntity> getOriginalOrderByAsqbh(String asqbh);
	boolean orderIDisExit(OriginalOrderEntity originalOrderEntity);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	int  insertIntoOriginalOrderFromLbRepayPlan(OriginalOrderEntity originalOrderEntity);
	List<OriginalOrderEntity> getAllOriginalOrder();
	boolean orderIDisExitInPonintRecord(OriginalOrderEntity originalOrderEntity);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	int  insertIntoPointRecordFromOriginalOrder(OriginalOrderEntity originalOrderEntity);
	List<UserEntity> getAllUserFromlbApplyMain();
	boolean isUserExitInUser(UserEntity userEntity);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	int  insertIntoUserFromLbApplyMain(UserEntity userEntity);
	boolean isNeedUpdateAZT(OriginalOrderEntity originalOrderEntity);
	int updateOriginalOrderAZT(OriginalOrderEntity originalOrderEntity);

}
