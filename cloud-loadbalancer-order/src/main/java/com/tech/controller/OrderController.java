package com.tech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@GetMapping("/home")
	public String home() {
		return "<h1>Order - Home page</h1>";
	}

	@GetMapping("/order")
	public String order() {
		return "<h1>Order - Order page</h1>";
	}

}
