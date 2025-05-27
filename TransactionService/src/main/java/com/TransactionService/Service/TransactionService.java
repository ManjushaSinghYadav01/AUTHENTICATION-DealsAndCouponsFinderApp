package com.TransactionService.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TransactionService.DTO.CouponDTO;
import com.TransactionService.DTO.DealDTO;
import com.TransactionService.DTO.TransactionRequest;
import com.TransactionService.DTO.TransactionResponse;
import com.TransactionService.FeignClients.CouponClient;
import com.TransactionService.FeignClients.DealClient;
import com.TransactionService.FeignClients.UserClient;
import com.TransactionService.Model.Transaction;
import com.TransactionService.Repository.TransactionRepository;

@Service
public class TransactionService {

    private static final Map<String, Double> categoryCashbackRates = Map.of(
        "Electronics", 0.05,
        "Fashion", 0.10,
        "Groceries", 0.02,
        "Others", 0.03
    );

    @Autowired
    private DealClient dealClient;

    @Autowired
    private CouponClient couponClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private TransactionRepository transactionRepo;

    //Make Transaction 
    public Transaction makeTransaction(TransactionRequest request) 
    {
    	
        //Fetch Deal
        DealDTO deal = dealClient.getDealById(request.getDealId());

        double price = deal.getPrice();

        // Apply Coupon (optional)
        double discount = 0.0;
        if (request.getCouponCode() != null)
        {
            CouponDTO coupon = couponClient.getCouponByCode(request.getCouponCode());

            if (coupon.getExpiryDate().isAfter(LocalDate.now()) && coupon.isActive()&& price >= coupon.getMinPurchaseAmount()
                && (coupon.getCategory().equalsIgnoreCase(deal.getCategory()) || coupon.getCategory().equalsIgnoreCase("ALL")))
            {
            	
                discount = Math.max(coupon.getFlatDiscount(),price * (coupon.getDiscountPercent() / 100));
            }
        }
        

        double priceAfterDiscount = price - discount;
        
        //Get user wallet balance
        double userWalletBalance = userClient.getUserWalletBalance(request.getEmail());

        //Deduct wallet points from price
        double walletUsed = Math.min(userWalletBalance, priceAfterDiscount);
        double finalPrice = priceAfterDiscount - walletUsed;

        // Calculate dynamic cashback
        double cashbackRate = categoryCashbackRates.getOrDefault(deal.getCategory(), 0.03);
        double cashback = finalPrice * cashbackRate;

        // Step 4: Save Transaction
        Transaction txn = new Transaction();
        txn.setEmail(request.getEmail());
        txn.setDealId(request.getDealId());
        txn.setCouponCode(request.getCouponCode());
        txn.setOriginalPrice(price);
        txn.setDiscountAmount(discount);
        txn.setFinalPrice(finalPrice);
        txn.setCashbackEarned(cashback); // store cashback
        txn.setTransactionTime(LocalDateTime.now());

        transactionRepo.save(txn);

        // Step 5: Update User Wallet
        userClient.updateWalletAfterTransaction(request.getEmail(),walletUsed, cashback);

        return txn;
    }

	public List<TransactionResponse> getUserHistoryByEmail(String email) {
		// TODO Auto-generated method stub
		 return transactionRepo.findByEmail(email);
	}
}
