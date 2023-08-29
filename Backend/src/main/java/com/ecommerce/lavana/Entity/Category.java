package com.ecommerce.lavana.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    @JsonIgnore
    private String img;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subcategory> subcategories;
}
