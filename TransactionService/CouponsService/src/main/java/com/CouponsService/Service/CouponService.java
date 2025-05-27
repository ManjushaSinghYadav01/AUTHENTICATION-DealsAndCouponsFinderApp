package com.CouponsService.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponsService.Repository.CouponRepository;
import com.CouponsService.Exception.CouponNotFoundException;
import com.CouponsService.Model.Coupon;

@Service
public class CouponService {

	 @Autowired
	    private CouponRepository couponRepository;

	

	public Coupon createCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return couponRepository.save(coupon);
	}
	
   public List<Coupon> createCoupons(List<Coupon> coupons) {
		
		return couponRepository.saveAll(coupons);
		
	}

	public List<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		 return couponRepository.findAll();
	}

	public Optional<Coupon> getCouponById(Long id) {
		// TODO Auto-generated method stub
		 return couponRepository.findById(id);
	}
	
	public Optional<List<Coupon>> getCouponByCategory(String category) {
		// TODO Auto-generated method stub
		return couponRepository.findByCategory(category);
	}

	public Optional<Coupon> getCouponByCode(String code) {
		// TODO Auto-generated method stub
		 return couponRepository.findByCode(code);
	}

	public Coupon updateCoupon(Long id, Coupon updatedCouponData) {
        Coupon existingCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException("Coupon with ID " + id + " not found."));

        
        existingCoupon.setCode(updatedCouponData.getCode());
        existingCoupon.setActive(updatedCouponData.isActive());
        existingCoupon.setDiscountPercent(updatedCouponData.getDiscountPercent());
        existingCoupon.setCategory(updatedCouponData.getCategory());
        existingCoupon.setFlatDiscount(updatedCouponData.getFlatDiscount());
        existingCoupon.setMinPurchaseAmount(updatedCouponData.getMinPurchaseAmount());
        existingCoupon.setExpiryDate(updatedCouponData.getExpiryDate());

        return couponRepository.save(existingCoupon);
    }
	
	public boolean deleteCoupon(Long id) {
		if (!couponRepository.existsById(id)) {
	       // return false; 
			 throw new CouponNotFoundException("Coupon not found with id: " + id);
	    }
	    
	    try {
	        couponRepository.deleteById(id);
	        return true;
	    } 
	    catch (Exception e) {
	        throw new RuntimeException("Error deleting coupon: " + e.getMessage());
	    }
		
	}
	
//	public boolean validateCoupon(String couponCode)
//	{
//		 Optional<Coupon> optionalCoupon = couponRepository.findByCode(couponCode);
//
//		 if (optionalCoupon.isPresent()) {
//		        Coupon coupon = optionalCoupon.get();
//
//		        if (coupon.isActive() && LocalDate.now().isBefore(coupon.getExpiryDate())) 
//		        {
//		            return true; 
//		        }
//		        else
//		        {
//		        	throw new CouponNotFoundException("Coupon is expired or inactive.");
//		        }
//		    }
//		 else
//		 {
//		    //return false; 
//			 throw new CouponNotFoundException("Invalid coupon code.");
//		 }
//	}


	

	
}
