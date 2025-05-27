package com.TransactionService.DTO;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {
//	 @Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(unique = true, nullable = false)
	    private String code;
	    private boolean active;
	    private double discountPercent;// e.g., 10% discount
	    private String category;  // Category-based discount
	    private double flatDiscount; // e.g., ₹500 discount
	    private double minPurchaseAmount; // Min purchase required
	    private LocalDate expiryDate;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public double getDiscountPercent() {
			return discountPercent;
		}
		public void setDiscountPercent(double discountPercent) {
			this.discountPercent = discountPercent;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public double getFlatDiscount() {
			return flatDiscount;
		}
		public void setFlatDiscount(double flatDiscount) {
			this.flatDiscount = flatDiscount;
		}
		public double getMinPurchaseAmount() {
			return minPurchaseAmount;
		}
		public void setMinPurchaseAmount(double minPurchaseAmount) {
			this.minPurchaseAmount = minPurchaseAmount;
		}
		public LocalDate getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(LocalDate expiryDate) {
			this.expiryDate = expiryDate;
		}

}
