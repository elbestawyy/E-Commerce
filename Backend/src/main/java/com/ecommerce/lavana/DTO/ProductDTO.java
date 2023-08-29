package com.ecommerce.lavana.DTO;

import com.ecommerce.lavana.Entity.Brand;
import lombok.Data;

@Data
public class ProductDTO {
    private String title;
    private String description;
    private int quantity;
    private double price;
    private double priceAfterDiscount;
    private String colour;
    private String imgCover;
    private Brand brand ;
}
