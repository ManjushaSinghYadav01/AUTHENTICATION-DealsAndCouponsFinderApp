package com.TransactionService.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TransactionService.DTO.UserDTO;

@FeignClient(name = "USER-SERVICE", path = "/user")
public interface UserClient {

	  @PutMapping("/walletUpdate/{email}")
	    public String updateWalletAfterTransaction(
	            @PathVariable String email,
	            @RequestParam double walletUsed,
	            @RequestParam double cashback);
	    
	    @GetMapping("/walletBalance/{email}")
	    public Double getUserWalletBalance(@PathVariable String email) ;
	    

}
