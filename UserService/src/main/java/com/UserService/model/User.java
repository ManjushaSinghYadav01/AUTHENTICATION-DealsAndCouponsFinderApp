package com.UserService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    private String name;
    
    @Column(nullable=false, unique=true)
    private String email;
    
    private String password; 
    
    private double walletPoint = 0.0;
    private String role; // Default role

    public User(Long userId, String name, String email, String password, double walletPoint, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.walletPoint = walletPoint;
		this.role = role; // Default role
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getWalletPoint() {
		return walletPoint;
	}

	public void setWalletPoint(double walletPoint) {
		this.walletPoint = walletPoint;
	}
	public double getRole() {
		return walletPoint;
	}

	public void setRole(double walletPoint) {
		this.walletPoint = walletPoint;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
