package com.shoppingShots.shoppingShots.controller;


import com.shoppingShots.shoppingShots.RequestObjects.ExternalUserAO;
import com.shoppingShots.shoppingShots.Utilities.CommonUtils;
import com.shoppingShots.shoppingShots.Utilities.OpResponse;
import com.shoppingShots.shoppingShots.model.Address;
import com.shoppingShots.shoppingShots.model.ExternalUser;
import com.shoppingShots.shoppingShots.model.InternalUser;
import com.shoppingShots.shoppingShots.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userService")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @PostMapping("saveUser")
    public OpResponse saveExternalUser(@RequestBody ExternalUserAO externalUserRequest){
        logger.info(CommonUtils.convertToJson(externalUserRequest));

        return userService.saveUser(externalUserRequest.getExternalUser(),externalUserRequest.getAddresses());
//        return new OpResponse("f",3);
    }
}
