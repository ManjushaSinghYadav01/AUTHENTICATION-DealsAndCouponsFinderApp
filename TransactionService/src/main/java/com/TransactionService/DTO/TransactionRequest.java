package com.TransactionService.DTO;

public class TransactionRequest {
	 private String email;
	    private Long dealId;
	    private String couponCode;
	    
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
		public TransactionRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		public TransactionRequest(String email, Long dealId, String couponCode) {
			super();
			this.email = email;
			this.dealId = dealId;
			this.couponCode = couponCode;
		}

}

