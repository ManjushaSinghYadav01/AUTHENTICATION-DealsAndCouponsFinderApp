package com.TransactionService.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String email;
    private Long dealId;
    private String couponCode;
    private double originalPrice;
    private double discountAmount;
    private double finalPrice;
   
    private double cashbackEarned;
    
    private LocalDateTime transactionTime;

    
	public Transaction(Long transactionId, String email, Long dealId, String couponCode, double originalPrice,
			double discountAmount, double finalPrice, double cashbackEarned, LocalDateTime transactionTime) {
		super();
		this.transactionId = transactionId;
		this.email = email;
		this.dealId = dealId;
		this.couponCode = couponCode;
		this.originalPrice = originalPrice;
		this.discountAmount = discountAmount;
		this.finalPrice = finalPrice;
		this.cashbackEarned = cashbackEarned;
		this.transactionTime = transactionTime;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public double getCashbackEarned() {
		return cashbackEarned;
	}

	public void setCashbackEarned(double cashbackEarned) {
		this.cashbackEarned = cashbackEarned;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	
    
	

}
