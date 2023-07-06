package com.tech.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${app.status.message}")
	private String message;

	@GetMapping("/test")
	public String getproduct() {
		System.out.println("Environment:: " + message);
		System.out.println("URL: " + url);
		System.out.println("Usr Name: " + username);
		System.out.println("Password: " + password);
		System.out.println("Driver Class Name: " + driverClassName);
		return message +": "+url +": "+username +": "+password +": "+driverClassName;
	}
	
}
