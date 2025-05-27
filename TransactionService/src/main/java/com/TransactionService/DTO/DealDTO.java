package com.TransactionService.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DealDTO {
	 private Long id;
	    private String name;
	    private String category;
	    private double price;
	    private double discount;
	    private boolean active;
	    private LocalDate expiryDate;
	    
	    
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
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public double getDiscount() {
			return discount;
		}
		public void setDiscount(double discount) {
			this.discount = discount;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public LocalDate getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(LocalDate expiryDate) {
			this.expiryDate = expiryDate;
		}
		public DealDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public DealDTO(Long id, String name, String category, double price, double discount, boolean active,
				LocalDate expiryDate) {
			super();
			this.id = id;
			this.name = name;
			this.category = category;
			this.price = price;
			this.discount = discount;
			this.active = active;
			this.expiryDate = expiryDate;
		}
	   
    
}
