package com.shoppingShots.shoppingShots.repository;

import com.shoppingShots.shoppingShots.model.Interfaces.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO,Integer> {

}
