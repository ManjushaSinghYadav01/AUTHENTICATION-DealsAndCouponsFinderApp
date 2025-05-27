package com.TransactionService.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.TransactionService.DTO.CouponDTO;

@FeignClient(name = "COUPON-SERVICE", path = "/coupons")
public interface CouponClient {

	  @GetMapping("/code/{code}")
	    public CouponDTO getCouponByCode(@PathVariable String code) ;




}
