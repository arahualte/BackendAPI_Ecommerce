package com.api.finalproject.products.service;

import java.util.List;

import com.api.finalproject.products.model.Product;
import com.api.finalproject.products.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productsRepository;

    public Product create(Product product) {
        return productsRepository.createProduct(product);
    }

    public Product getProductById(String id) {
        return productsRepository.findProductById(id);
    }

    public List<Product> getAllProducts() {
        return productsRepository.findAllProducts();
    }

    public String deleteById(String id) {
        return productsRepository.deleteProductById(id);
    }
}
