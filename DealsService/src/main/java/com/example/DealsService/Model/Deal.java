package com.example.DealsService.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "deals")
public class Deal {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
		public Deal() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Deal(Long id, String name, String category, double price, double discount, boolean active,
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
