package com.example.Gateway.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Gateway.Dto.EventRequest;

@FeignClient(
name="account-service",
url="http://localhost:8081")

public interface AccountClient {

	@PostMapping(
			"/accounts/{accountId}/transactions")
			void applyTransaction(
			@PathVariable String accountId,
			@RequestBody EventRequest request);
	
}
