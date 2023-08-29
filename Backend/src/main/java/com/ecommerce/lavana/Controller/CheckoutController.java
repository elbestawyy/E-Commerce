package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.DTO.Purchase;
import com.ecommerce.lavana.DTO.PurchaseResponse;
import com.ecommerce.lavana.service.CheckoutService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("http://localhost:4200")
public class CheckoutController {
    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/secure/purchase")
    public PurchaseResponse placeOrder(@RequestHeader(value = "Authorization") String token,
            @RequestBody Purchase purchase)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("sign in to complete this order");
        }
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase,user);
        return purchaseResponse;
    }

}
