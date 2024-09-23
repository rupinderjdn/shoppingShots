package com.shoppingShots.shoppingShots.service;

import com.shoppingShots.shoppingShots.Utilities.OpResponse;
import com.shoppingShots.shoppingShots.model.Address;
import com.shoppingShots.shoppingShots.model.Interfaces.UserDO;

import java.util.List;

public interface UserService {
    public OpResponse saveUser(UserDO user, List<Address> address);
    public UserDO findByExample(UserDO user);
}
