package com.tech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {

	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	// RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/getOreder")
	@CircuitBreaker(name = "getOrederCB", fallbackMethod = "getOrderFallback")
	public String getOreder() {
		logger.info("getOreder() call starts here");
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8282/order", String.class);
		logger.info("Response :" + entity.getStatusCode());
		return entity.getBody();
	}

	@GetMapping("/getProduct")
	@CircuitBreaker(name = "getProductCB", fallbackMethod = "getProductFallback")
	public String getProduct() {
		logger.info("getProduct() call starts here");
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8383/product", String.class);
		logger.info("Response :" + entity.getStatusCode());
		return entity.getBody();
	}

	public String getOrderFallback(Exception e) {
		logger.info("---RESPONSE FROM ORDER FALLBACK METHOD---");
		return "server is down, please try after sometime!!!";
	}

	public String getProductFallback(Exception e) {
		logger.info("---RESPONSE FROM PRODUCT FALLBACK METHOD---");
		return "server is down, please try after sometime!!!";
	}

}
