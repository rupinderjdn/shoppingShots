package com.shoppingShots.shoppingShots.repository;

import com.shoppingShots.shoppingShots.model.Category;
import com.shoppingShots.shoppingShots.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
