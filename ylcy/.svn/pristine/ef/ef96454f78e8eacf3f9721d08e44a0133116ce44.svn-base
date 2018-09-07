package com.ylch.ylcy.ylcy.user.bean;

public class ResponseBean {
	

	

	public ResponseBean(String resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public ResponseBean( Object data,String resultCode, String resultMessage) {
		super();
		this.data = data;
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	// http 状态码
    private String resultCode;

    // 返回信息
    private String resultMessage;

    // 返回的数据
    private Object data;



	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "data=" + data + ", resultCode=" + resultCode + ", resultMessage=" + resultMessage ;
	}

	
	
    

}
