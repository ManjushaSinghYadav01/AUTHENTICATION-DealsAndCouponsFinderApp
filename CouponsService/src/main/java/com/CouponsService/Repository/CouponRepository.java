package com.CouponsService.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CouponsService.Model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Optional<Coupon> findByCode(String couponCode);

	Optional<List<Coupon>> findByCategory(String category);

}
	