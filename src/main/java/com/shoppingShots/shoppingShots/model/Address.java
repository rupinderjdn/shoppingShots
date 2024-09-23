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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String houseNo;
    private String state;
    private String pinCode;
    private String city;
    private String district;
    private String streetNo;
    private String landmark;
    private String floorNo;
}
