package com.TransactionService.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.TransactionService.DTO.DealDTO;

@FeignClient(name = "DEAL-SERVICE", path = "/deals")
public interface DealClient {
	  @GetMapping("/id/{id}")
	    DealDTO getDealById(@PathVariable Long id);
	 
}
