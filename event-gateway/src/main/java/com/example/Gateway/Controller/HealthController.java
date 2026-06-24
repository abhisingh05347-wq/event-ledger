package com.example.Gateway.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

public class HealthController {

	 @GetMapping("/health")
	    public Map<String, String> health() {

	        Map<String, String> response =
	                new HashMap<>();

	        response.put("status", "UP");
	        response.put("service",
	                "event-gateway");

	        return response;
	    }
}
