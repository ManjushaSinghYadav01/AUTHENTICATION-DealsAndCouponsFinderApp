package com.example.DealsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class DealsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealsServiceApplication.class, args);
	}

}
