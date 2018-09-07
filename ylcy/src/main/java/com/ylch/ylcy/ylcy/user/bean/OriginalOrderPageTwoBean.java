package com.ylch.ylcy.ylcy.user.bean;

import java.io.Serializable;
import java.util.List;

public class OriginalOrderPageTwoBean implements Serializable{
	 private List<OriginalOrderByStagesRecordBean> OriginalOrderByStagesRecordBeanList;//分期详细信息
	 private OriginalOrderPageTotalInfoBean OriginalOrderPageTotalInfoBean;//分期总览
	public List<OriginalOrderByStagesRecordBean> getOriginalOrderByStagesRecordBeanList() {
		return OriginalOrderByStagesRecordBeanList;
	}
	public void setOriginalOrderByStagesRecordBeanList(
			List<OriginalOrderByStagesRecordBean> originalOrderByStagesRecordBeanList) {
		OriginalOrderByStagesRecordBeanList = originalOrderByStagesRecordBeanList;
	}
	public OriginalOrderPageTotalInfoBean getOriginalOrderPageTotalInfoBean() {
		return OriginalOrderPageTotalInfoBean;
	}
	public void setOriginalOrderPageTotalInfoBean(OriginalOrderPageTotalInfoBean originalOrderPageTotalInfoBean) {
		OriginalOrderPageTotalInfoBean = originalOrderPageTotalInfoBean;
	}
	
	 
}
