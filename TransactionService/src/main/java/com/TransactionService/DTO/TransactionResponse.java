package com.TransactionService.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

	 private Long transactionId;
	    private double amount;
	    private double cashback;
	    private LocalDateTime timestamp;
		public Long getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(Long transactionId) {
			this.transactionId = transactionId;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public double getCashback() {
			return cashback;
		}
		public void setCashback(double cashback) {
			this.cashback = cashback;
		}
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
		public TransactionResponse(Long transactionId, double amount, double cashback, LocalDateTime timestamp) {
			super();
			this.transactionId = transactionId;
			this.amount = amount;
			this.cashback = cashback;
			this.timestamp = timestamp;
		}
		public TransactionResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
			
		
	    
	    
	    
}

