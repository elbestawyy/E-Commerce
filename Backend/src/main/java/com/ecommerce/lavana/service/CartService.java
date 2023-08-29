package com.ecommerce.lavana.service;

import com.ecommerce.lavana.DTO.CartDTO;
import com.ecommerce.lavana.DTO.CartItemDTO;

public interface CartService {
    public void addToCart(String user, CartItemDTO cartItemDto)throws Exception;
    public CartDTO listCartItems(String user)throws Exception;
    public void deleteCartItem(String user , Long itemId) throws Exception;
}
