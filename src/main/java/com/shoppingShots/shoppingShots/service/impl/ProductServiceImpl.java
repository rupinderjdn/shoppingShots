package com.shoppingShots.shoppingShots.service.impl;

import com.shoppingShots.shoppingShots.model.Product;
import com.shoppingShots.shoppingShots.repository.ProductRepository;
import com.shoppingShots.shoppingShots.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll() ;
    }

    @Override
    public Boolean deleteProduct(int id) {
        Product product = productRepository.findById(id).orElse(null);
        assert product != null;
        productRepository.delete(product);
        return true;
    }

    @Override
    public Product getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }
}
