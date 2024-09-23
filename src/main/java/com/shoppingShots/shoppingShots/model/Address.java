package com.shoppingShots.shoppingShots.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String houseNo;
    private String state;
    private String pinCode;
    private String city;
    private String district;
    private String streetNo;
    private String landmark;
    private String floorNo;
}
