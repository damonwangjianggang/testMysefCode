package com.ylch.ylcy.ylcy.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
	
	private final static Logger LoGGER = LoggerFactory.getLogger(ExceptionHandle.class);
	
	/**
	 * 判断错误是否市已定义的已知错误，不是则由未知错误代替，同时记录在log中
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result exceptionGet(Exception e) {
		if(e instanceof DescribeException) {
			DescribeException myException = (DescribeException) e;
			return ResultUtil.error(myException.getCode(),myException.getMessage());
		}
		LoGGER.error("【系统异常】{}",e);
         return ResultUtil.error(ExceptionEnum.UNKNOW_ERROR);
	}

}
