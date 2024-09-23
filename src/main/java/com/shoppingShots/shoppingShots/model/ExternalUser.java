package com.shoppingShots.shoppingShots.model;

import com.shoppingShots.shoppingShots.model.Interfaces.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUser extends User {
    private Address address;
    private String billingInfo;
    private String preferredLanguage;
    private LocalDateTime lastOrderDate;
    private List<Integer> productsPurchased;
    private List<Integer> currentCart;
    private List<Integer> wishList;
}
