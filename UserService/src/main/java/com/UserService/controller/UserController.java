package com.UserService.controller;

import com.UserService.dto.*;
import com.UserService.exceptions.UserAlreadyExistsException;
import com.UserService.exceptions.UserNotFoundException;
import com.UserService.model.User;
import com.UserService.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
  
    //Register
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest request){
        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }
   
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
    
 

    @GetMapping("/profile/{email}")
    public ResponseEntity<UserProfileDto> getProfile(@PathVariable String email) {
        UserProfileDto profile = userService.getProfile(email);
        return ResponseEntity.ok(profile);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/walletUpdate/{email}")
    public ResponseEntity<String> updateWalletAfterTransaction(
            @PathVariable String email,
            @RequestParam double walletUsed,
            @RequestParam double cashback) {
        userService.updateWalletAfterTransaction(email, walletUsed, cashback);
        return ResponseEntity.ok("Wallet updated successfully.");
    }
    @GetMapping("/walletBalance/{email}")
    public ResponseEntity<Double> getUserWalletBalance(@PathVariable String email) {
        Double balance = userService.getUserWalletBalance(email);
        return ResponseEntity.ok(balance);
    }
    @PostMapping("/createUsers")
    public ResponseEntity<List<UserDto>> createUsers(@RequestBody List<User> users) {
        List<UserDto> createdUsers = userService.createUsers(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsers);
    }


}
