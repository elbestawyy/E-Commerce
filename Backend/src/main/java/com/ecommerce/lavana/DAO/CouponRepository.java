package com.ecommerce.lavana.DAO;

import com.ecommerce.lavana.Entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Page<Coupon> findCouponByCode(@RequestParam("coupon_code") String code, Pageable pageable);
    Page<Coupon> findCouponById(@RequestParam("coupon_id") Long id ,Pageable pageable);
    Optional<Coupon>findCouponByCode(String code);
}
