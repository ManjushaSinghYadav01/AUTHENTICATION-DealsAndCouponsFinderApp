package com.UserService.dto;

import lombok.Data;

@Data
public class UserProfileDto {
	  private Long id;
	    private String name;
	    private String email;
	    private double walletPoints;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		public double getWalletPoints() {
			return walletPoints;
		}
		public void setWalletPoints(double walletPoints) {
			this.walletPoints = walletPoints;
		}
	    

}
