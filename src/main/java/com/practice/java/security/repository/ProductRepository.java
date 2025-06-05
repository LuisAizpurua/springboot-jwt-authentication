package com.practice.java.security.repository;

import com.practice.java.security.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

//    @Query("select distinct p from User u join u.product_id p")
//    List<Product> findAllProductsRelatedToUsers();
}
