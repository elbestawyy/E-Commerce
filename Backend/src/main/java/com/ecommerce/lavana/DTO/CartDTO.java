package com.ecommerce.lavana.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private List<CartItemDTO> cartItem;
    private double totalCost;
}
