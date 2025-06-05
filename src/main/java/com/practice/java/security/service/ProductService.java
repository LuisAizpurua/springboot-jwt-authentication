package com.practice.java.security.service;

import com.practice.java.security.entities.Product;
import com.practice.java.security.interfaces.IProductService;
import com.practice.java.security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Product findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll(){
        return repository.findAll();
//        return repository.findAllProductsRelatedToUsers();
    }

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Transactional
    @Override
    public Product updateProduct(Product product) {
        return repository.save(product);
    }
}
