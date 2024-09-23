package com.shoppingShots.shoppingShots.model;

import com.shoppingShots.shoppingShots.model.Interfaces.UserDO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUser extends UserDO {
    private List<Integer> addressIds;
    private String billingInfo;
    private String gender;
    private String preferredLanguage;
    private LocalDateTime lastOrderDate;
    private List<Integer> productsPurchased;
    private List<Integer> currentCart;
    private List<Integer> wishList;
}
