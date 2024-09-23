package com.shoppingShots.shoppingShots.RequestObjects;

import com.shoppingShots.shoppingShots.model.Address;
import com.shoppingShots.shoppingShots.model.ExternalUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalUserAO {
    private ExternalUser externalUser;
    private List<Address> addresses;
}
