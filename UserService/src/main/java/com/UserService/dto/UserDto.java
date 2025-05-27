package com.UserService.dto;


public class UserDto {
    private Long id;
    private String name;
    private String email;
    private double walletBalance;

    // Constructors
    public UserDto() {}

    public UserDto(Long id, String name, String email, double walletBalance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.walletBalance = walletBalance;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getWalletBalance() { return walletBalance; }
    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }
}

