package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.Entity.Coupon;
import com.ecommerce.lavana.service.CouponService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon/secure")
@CrossOrigin("http://localhost:4200")
public class CouponController {
    private CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/add")
    public void addCoupon(@RequestHeader(value = "Authorization") String token,
                          @RequestBody Coupon coupon)throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        couponService.postCoupon(coupon);
    }

    @PutMapping("/update")
    public Coupon putCoupon(@RequestHeader(value = "Authorization") String token,
                            @RequestParam("coupon_id") Long couponId,
                            @RequestBody Coupon coupon) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        return couponService.putCoupon(couponId,coupon);
    }

    @DeleteMapping("/delete")
    public void deleteCoupon(@RequestHeader(value = "Authorization") String token,
                             @RequestParam("coupon_id") Long couponId)throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        couponService.deleteCoupon(couponId);
    }
}
