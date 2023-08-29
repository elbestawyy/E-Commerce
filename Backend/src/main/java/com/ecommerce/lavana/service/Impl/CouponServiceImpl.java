package com.ecommerce.lavana.service.Impl;

import com.ecommerce.lavana.DAO.CouponRepository;
import com.ecommerce.lavana.Entity.Coupon;
import com.ecommerce.lavana.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CouponServiceImpl implements CouponService {
    private CouponRepository couponRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public void postCoupon(Coupon couponDTO) {
        Coupon coupon = new Coupon();
        coupon.setCode(couponDTO.getCode());
        coupon.setExpiredDate(couponDTO.getExpiredDate());
        coupon.setDiscountPercentage(couponDTO.getDiscountPercentage());
        couponRepository.save(coupon);
    }

    @Override
    public Coupon putCoupon(Long couponId, Coupon couponDTO) throws Exception {
        Optional<Coupon> coupon = couponRepository.findById(couponId);
        if (coupon.isEmpty()) {
            throw new Exception("Coupon not found");
        }
        coupon.get().setDiscountPercentage(couponDTO.getDiscountPercentage());
        coupon.get().setCode(couponDTO.getCode());
        coupon.get().setExpiredDate(couponDTO.getExpiredDate());
        return couponRepository.save(coupon.get());
    }

    @Override
    public void deleteCoupon(Long couponId) throws Exception{
        Optional<Coupon> coupon = couponRepository.findById(couponId);
        if (coupon.isEmpty()) {
            throw new Exception("Coupon not found");
        }
        couponRepository.delete(coupon.get());
    }
}
