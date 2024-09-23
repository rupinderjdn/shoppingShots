package com.shoppingShots.shoppingShots.service;

import com.shoppingShots.shoppingShots.model.Product;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);
    public List<Product> getAllProducts();
    public Boolean deleteProduct(int id);
    public Product getProductById(int id);
}
