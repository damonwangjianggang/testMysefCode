package com.ylch.ylcy.ylcy.user.spring;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ylch.ylcy.ylcy.user.exception.ExceptionHandle;
import com.ylch.ylcy.ylcy.user.exception.Result;



@Aspect
@Component
public class LoggerAndException {

	@Autowired
	private ExceptionHandle exceptionHandle;
	
	private Logger logger =LoggerFactory.getLogger(getClass());
	@Pointcut("execution(public * com.ylch.ylcy.ylcy.*.controller..*.*(..))")
	public void controllerLog() {
		
	}
	@Pointcut("execution(public * com.ylch.ylcy.ylcy.*.service..*.*(..))")
	public void serviceLog() {
		
	}
	
	@Before("controllerLog()")
	public void doControllerBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		//记录请求内容
		 logger.info("URL:"+request.getRequestURL().toString());
		 logger.info("HTTP_METHOD: "+request.getMethod());
		 logger.info("IP: "+request.getRemoteAddr());
		 logger.info("access_token"+request.getHeader("access_token"));
		 Enumeration<String> enu = request.getParameterNames();
		 while (enu.hasMoreElements()) {
			 String name = (String)enu.nextElement();
			 logger.info("name:{},value:{}",name,request.getParameter(name));
		 }
	}
	@Before("serviceLog()")
	public void doServiceBefore(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName(); 
		String methodName = joinPoint.getSignature().getName(); 
		StringBuilder log = new StringBuilder(); 
		log.append("before Service: ") 
		.append(className) 
		.append("@") 
		.append(methodName) 
		.append(" , params: "); 
		Object[] args = joinPoint.getArgs(); 
		for (Object arg : args) { 
			
		log.append(arg.toString()+ ", "); 
		} 
		logger.info(log.toString()); 
		}
	
	@Around("controllerLog()")
	public Object doControllerAround(ProceedingJoinPoint  proceedingJoinPoint) throws Throwable  {
		 Result result = null;
		try {
			
		}catch (Exception e) {
			return exceptionHandle.exceptionGet(e);
		}
		  if(result == null){
	            return proceedingJoinPoint.proceed();
	        }else {
	            return result;
	        }
		
	}
	@Around("serviceLog()")
	public Object doServiceAround(ProceedingJoinPoint  proceedingJoinPoint) throws Throwable  {
		 Result result = null;
		try {
			
		}catch (Exception e) {
			return exceptionHandle.exceptionGet(e);
		}
		  if(result == null){
	            return proceedingJoinPoint.proceed();
	        }else {
	            return result;
	        }
		
	}
	
	@AfterReturning(returning ="ret",pointcut = "controllerLog()")
	public void doControllerAfterReturning(Object ret) {
		logger.info("Controller_RESPONSE: "+ret);
	}
	@AfterReturning(returning ="ret",pointcut = "serviceLog()")
	public void doServiceAfterReturning(Object ret) {
		logger.info("Service_RESPONSE: "+ret);
	}
	
}
