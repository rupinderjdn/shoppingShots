package com.shoppingShots.shoppingShots.service;

import com.shoppingShots.shoppingShots.model.Category;
import org.springframework.data.domain.Example;

import java.util.List;

public interface CategoryService {
    public Boolean existCategory(String categoryName);
    public Category saveCategory(Category category);
    public List<Category> getAllCategory();
    public List<Category> getAllActiveCategory();
    public Boolean deleteCategory(Integer id);
    public Category getCategoryById(Integer id);
    public List<Category> getCategoryByExample(Example<Category> category);
}
