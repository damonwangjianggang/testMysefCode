package com.ylch.ylcy.ylcy.user.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Component;



public class HttpClientUtileForSMSGETRequest {
	/**
	 * @param args 
	 * @throws IOException 
	 */
/*	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String uid="810851";
		String auth="00b3710cfa5b01b374356442cab68115";
		String mobile="17621830876"; 
		//int mobile_code = (int)(( Math.random()*9+1)*100000);//验证码 
		int mobile_code = (int) ((Math.random()*9+1)*100000);
		String registerContent = "您正在注册，您的验证码是";
		String reset ="您正在重置密码，您的验证码是";
		String content="您的验证码是： "+mobile_code;
		//发送 即时信息 get 方式 使用GBK编码 
		//String urlstr="http://sms.10690221.com:9011/hy?uid="+uid+"&auth="+auth+"&mobile="+mobile+"&msg="+URLEncoder.encode(content,"gbk")+"&expid=0";
		String urlstr="http://sms.10690221.com:9011/hy?uid=810851&auth=00b3710cfa5b01b374356442cab68115&mobile="+mobile+"&msg="+URLEncoder.encode(content,"gbk")+"&expid=0";
		HttpClientUtileForSMSGETRequest t=new HttpClientUtileForSMSGETRequest();	 
		String str=t.doGetRequest(mobile,content);
		System.out.println("响应:"+str);  
		
	}*/
	
	public static String  doGetRequest(String mobile,String content) {
		String urlstr = null;
		try {
			urlstr = "http://sms.10690221.com:9011/hy?uid=810851&auth=00b3710cfa5b01b374356442cab68115&mobile="+mobile+"&msg="+URLEncoder.encode(content,"gbk")+"&expid=0";
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String res = null;
		HttpClient client = new HttpClient(
				new MultiThreadedHttpConnectionManager());
		client.getParams().setIntParameter("http.socket.timeout", 10000);
		client.getParams().setIntParameter("http.connection.timeout", 5000);

		HttpMethod httpmethod = new GetMethod(urlstr);
		try {
			int statusCode = client.executeMethod(httpmethod);
			if (statusCode == HttpStatus.SC_OK) {
				res = httpmethod.getResponseBodyAsString();
			}
		} catch (HttpException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		} finally {
			httpmethod.releaseConnection();
		}
		return res;
	} 

}
