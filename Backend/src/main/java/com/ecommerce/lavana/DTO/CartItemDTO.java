package com.ecommerce.lavana.DTO;

import com.ecommerce.lavana.Entity.Cart;
import com.ecommerce.lavana.Entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private int quantity;
    private Product product;

    public CartItemDTO(Cart cart){
        this.id = cart.getId();
        this.quantity=cart.getQuantity();
        this.product = cart.getProduct();
    }
}
