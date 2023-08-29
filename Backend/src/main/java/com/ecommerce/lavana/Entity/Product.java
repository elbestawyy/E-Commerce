package com.ecommerce.lavana.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "price_after_discount")
    private double priceAfterDiscount;

    @Column(name = "colour")
    private String colour;

    @Column(name = "img_cover")
    private String imgCover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<WishList> wishLists;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cart> carts;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private OrderItem orderItem;

    public Product(String title, String description, int quantity, double price, double priceAfterDiscount, String colour, String imgCover, Brand brand) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.priceAfterDiscount = priceAfterDiscount;
        this.colour = colour;
        this.imgCover = imgCover;
        this.brand = brand;
    }
}
