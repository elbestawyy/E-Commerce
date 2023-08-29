package com.ecommerce.lavana.service;

import com.ecommerce.lavana.Entity.Coupon;

public interface CouponService {
    public void postCoupon(Coupon couponDTO);
    public Coupon putCoupon(Long couponId, Coupon couponDTO) throws Exception;
    public void deleteCoupon(Long couponId) throws Exception;
}
