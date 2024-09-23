package com.shoppingShots.shoppingShots.repository;

import com.shoppingShots.shoppingShots.model.Category;
import com.shoppingShots.shoppingShots.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    public List<Product> findByIsActiveTrue();
}
