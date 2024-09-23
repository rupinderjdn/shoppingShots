package com.shoppingShots.shoppingShots.service.impl;

import com.shoppingShots.shoppingShots.model.Category;
import com.shoppingShots.shoppingShots.repository.CategoryRepository;
import com.shoppingShots.shoppingShots.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoryserviceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryserviceImpl.class);
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Boolean existCategory(String categoryName) {
        return categoryRepository.existsByName(categoryName);
    }

    @Override
    public Category saveCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllActiveCategory() {
        return categoryRepository.findByIsActiveTrue();
    }

    @Override
    public Boolean deleteCategory(int id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(category)){
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }

    @Override
    public Category getCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElse(null);

        return category;
    }

    @Override
    public List<Category> getCategoryByExample(Example<Category> example) {
        logger.info(example.getProbe().toString());
        List<Category> category = categoryRepository.findAll(example);
        return category;
    }

}
