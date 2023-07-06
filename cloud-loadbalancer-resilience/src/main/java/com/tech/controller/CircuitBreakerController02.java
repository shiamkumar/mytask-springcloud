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
public class CircuitBreakerController02 {

	Logger logger = LoggerFactory.getLogger(CircuitBreakerController02.class);

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/getPurchaseWithoutCB")
	public String getPurchaseWithoutCirciutBreaker() {
		logger.info("getOreder() call starts here");
		ResponseEntity<String> response01 = restTemplate.getForEntity("http://localhost:8282/order", String.class);
		logger.info("Response :" + response01.getStatusCode());
		return response01.getBody();
	}

	@GetMapping("/ggetPurchaseWithCB")
	@CircuitBreaker(name = "orderCB", fallbackMethod = "getOrderFallback")
	public String getPurchaseCirciutBreaker() {
		logger.info("getOrder() call starts here");
		ResponseEntity<String> response01 = restTemplate.getForEntity("http://localhost:8282/order", String.class);
		logger.info("Response :" + response01.getStatusCode());
		return response01.getBody();
	}

	public String getOrderFallback(Exception e) {
		logger.info("---RESPONSE FROM FALLBACK METHOD---");
		return "server is down, please try after sometime!!!";
	}
}
