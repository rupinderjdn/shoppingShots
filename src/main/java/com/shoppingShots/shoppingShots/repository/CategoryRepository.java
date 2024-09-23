package com.shoppingShots.shoppingShots.repository;

import com.shoppingShots.shoppingShots.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// don't know why second argument is an integer
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    public Boolean existsByName(String name);
}
