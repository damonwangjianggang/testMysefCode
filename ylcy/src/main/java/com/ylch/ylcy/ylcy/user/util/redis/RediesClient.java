package com.ylch.ylcy.ylcy.user.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class RediesClient {
	@Autowired
	private Environment env; 
	Jedis  jedis = JedisPoolUtils.getJedis();
	public boolean isExistCheckCode(String phone,String code) {
		 if(jedis.exists(phone)&&code.equals(jedis.get(phone))){
			 return true;
		 } else {
			 return false;
		 }
	}
	public boolean saveCheckCode(String phone,String checkCode) {
		if(jedis.exists(phone)) {
			return false;
		}else{
			jedis.set(phone, checkCode);
			int a =Integer.parseInt(env.getProperty("spring.redis.timeout"));
			jedis.expire(phone, a);
			return true;
		}
	}
	public boolean isExistCheckCode(String phone) {
		if(jedis.exists(phone)) {
			return true;
		}else {
			return false;	
		}
	}

}
