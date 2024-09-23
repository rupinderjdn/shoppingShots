package com.shoppingShots.shoppingShots.controller;

import com.shoppingShots.shoppingShots.model.Category;
import com.shoppingShots.shoppingShots.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("")
    public List<Category> index()
    {
        logger.info("Here in the test");
        return categoryService.getAllCategory();
    }
}
