package com.practice.java.security.interfaces;

import com.practice.java.security.entities.Product;

import java.util.List;

public interface IProductService {
    Product findById(Integer id);
    boolean deleteById(Integer id);

    List<Product> findAll();

    Product saveProduct(Product product);

    Product updateProduct(Product product);
}
