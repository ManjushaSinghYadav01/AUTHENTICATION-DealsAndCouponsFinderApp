package com.security.controller;

import com.security.dto.AuthRequest;
import com.security.entity.UserCredential;
import com.security.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest authRequest) {
        
        try {    
    	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
            	System.out.println("Authenticating user: " + authRequest.getEmail());
                System.out.println("AuthenticationManager instance: " + authenticationManager);
                return service.generateToken(authRequest.getEmail());
            }
            else
            {
            System.out.println("Invaid access"); 
            throw new RuntimeException("Invalid credentials");
            }
        } catch (Exception e) {
        				System.out.println("Authentication failed for user: " + authRequest.getEmail());
        				e.printStackTrace();
			throw new RuntimeException("Invalid credentials", e);
        }
    
    }
 
    
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        try {
            service.validateToken(token);
            return "Token is valid";
        } catch (Exception e) {
            return "Invalid token";
        }
    }

}
