package com.ecommerce.lavana.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    @Column(name = "expire_date")
    private Date expiredDate;

}
