package com.shoppingShots.shoppingShots.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(length = 500)
    private String title;

    @Column(length = 5000)
    private String description;

    // TODO this should be the object or Id
    private String category;
    private Double price;
    private Double discount;
    private Double discountPrice;
    private int stock;
    private String image;
    private boolean isActive;
}
