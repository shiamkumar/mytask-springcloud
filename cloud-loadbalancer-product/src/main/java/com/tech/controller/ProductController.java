package com.tech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@GetMapping("/home")
	public String home() {
		return "<h1>Product - Home page</h1>";
	}

	@GetMapping("/product")
	public String product() {
		return "<h1>Product - Product page</h1>";
	}

}
