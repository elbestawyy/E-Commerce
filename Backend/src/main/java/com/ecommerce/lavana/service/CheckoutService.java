package com.ecommerce.lavana.service;

import com.ecommerce.lavana.DTO.Purchase;
import com.ecommerce.lavana.DTO.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase,String email);
}
