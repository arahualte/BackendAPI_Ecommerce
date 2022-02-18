package com.api.finalproject.products.controller;

import java.util.List;

import com.api.finalproject.products.model.Product;
import com.api.finalproject.products.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productsService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productsService.create(product);
    }

    @GetMapping(params = "id")
    public Product getProductById(@RequestParam String id) {
        return productsService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAll() {
        return productsService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        return productsService.deleteById(id);
    }

}
