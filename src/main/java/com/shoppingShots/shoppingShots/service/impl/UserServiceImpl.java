package com.shoppingShots.shoppingShots.service.impl;

import com.shoppingShots.shoppingShots.Utilities.OpResponse;
import com.shoppingShots.shoppingShots.model.Address;
import com.shoppingShots.shoppingShots.model.ExternalUser;
import com.shoppingShots.shoppingShots.model.Interfaces.UserDO;
import com.shoppingShots.shoppingShots.model.InternalUser;
import com.shoppingShots.shoppingShots.repository.AddressRepository;
import com.shoppingShots.shoppingShots.repository.UserRepository;
import com.shoppingShots.shoppingShots.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public OpResponse saveUser(UserDO user, List<Address> address) {
        try{
            UserDO queryUser = new UserDO();
            queryUser.setEmail(user.getEmail());
            if(findByExample(queryUser) != null){
                return new OpResponse("User exists with email id :-"+user.getEmail(),400);
            }
            if(user instanceof InternalUser){
                UserDO user1  = userRepository.save(user);
                return new OpResponse("Save successfully with id"+user1.getId(),200);
            }
            else if(user instanceof ExternalUser){
                ExternalUser externalUser = (ExternalUser) user;
                List<Address> address1 = addressRepository.saveAll(address);
                if(address1!=null){
                    List<Integer> addressIds = address1.stream().map((address2 -> address2.getId())).collect(Collectors.toUnmodifiableList());
                    externalUser.setAddressIds(addressIds);
                    UserDO user1  = userRepository.save(user);
                    return new OpResponse("Save successfully",200);
                }
                else{
                    return new OpResponse("Address could not be saved",400);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new OpResponse("Error while saving",400);
        }
        return null;
    }

    @Override
    public UserDO findByExample(UserDO userDO){
        return userRepository.findOne(Example.of(userDO)).orElse(null);
    }

}
