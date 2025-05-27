package com.UserService.service;

import com.UserService.dto.*;
import com.UserService.exceptions.InvalidInputException;
import com.UserService.exceptions.UserAlreadyExistsException;
import com.UserService.exceptions.UserNotFoundException;
import com.UserService.model.User;
import com.UserService.repository.UserRepository;

import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
 

    //Registration
    public void registerUser(SignupRequest request) throws UserAlreadyExistsException 
    {
    	request.setPassword(request.getPassword());
    	
        // Email format validation
        if (!request.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidInputException("Invalid email format");
        }

        // Password strength validation
        if (request.getPassword().length() < 8) {
            throw new InvalidInputException("Password must be at least 8 characters long");
        }
        
        if (userRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // No encoding
        user.setWalletPoint(0.0); // Default wallet value

        userRepository.save(user);
        
    }
    
//    public String generateToken(String userName)
//    {
//    	return jwtService.generateToken(userName);
//    	 
//    }
//    public void validateToken(String token)
//    {
//    	jwtService.validateToken(token);
//    }

 

	
    public String login(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            return "Login successful";
        }
        else {
            throw new UserNotFoundException("Invalid email or password");
        }
    }

    
    //Get Profile
    public UserProfileDto getProfile(String email)
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setWalletPoints(user.getWalletPoint());

        return dto;
    }

    //List of user profiles by admin
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //Call by Transaction Services
    public void updateWalletAfterTransaction(String email, double walletUsed, double cashback) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        double updatedWallet = user.getWalletPoint() - walletUsed + cashback;
        user.setWalletPoint(updatedWallet);
        userRepository.save(user);
    }

    public Double getUserWalletBalance(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.getWalletPoint();
    }

    //List of user Registration by admin
    public List<UserDto> createUsers(List<User> users) {
        return userRepository.saveAll(users)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //To Show user Profile By Without password
    public UserDto convertToDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getWalletPoint()
        );
    }
}