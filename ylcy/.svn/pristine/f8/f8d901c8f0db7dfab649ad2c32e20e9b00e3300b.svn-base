package com.ylch.ylcy.ylcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*@SpringBootApplication
@EnableScheduling
public class YlcyApplication {

	public static void main(String[] args) {
		SpringApplication.run(YlcyApplication.class, args);
	}
}*/
@SpringBootApplication
@EnableScheduling
public class YlcyApplication extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YlcyApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(YlcyApplication.class, args);
    }
}
