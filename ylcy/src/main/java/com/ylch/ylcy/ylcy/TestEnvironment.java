package com.ylch.ylcy.ylcy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEnvironment {
	
	@RequestMapping("/testEnvironment")
	public String testEnvironment() {
		return "Hello World0021";
	}
}
