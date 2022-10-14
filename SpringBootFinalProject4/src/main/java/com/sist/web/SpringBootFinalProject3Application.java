package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication			// 메모리 할당
@ComponentScan(basePackages = {"com.sist.web.controller", "com.sist.web.dao", "com.sist.web.manager"})
public class SpringBootFinalProject3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFinalProject3Application.class, args);
	}

}
