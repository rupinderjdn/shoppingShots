package com.shoppingShots.shoppingShots.service;

import com.shoppingShots.shoppingShots.model.Category;

import java.util.List;

public interface CategoryService {
    public Boolean existCategory(String categoryName);
    public Category saveCategory(Category category);
    public List<Category> getAllCategory();
    public Boolean deleteCategory(int id);
    public Category getCategoryById(int id);
}
