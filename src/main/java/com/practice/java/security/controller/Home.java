package com.practice.java.security.controller;

import com.practice.java.security.dto.ProductDto;
import com.practice.java.security.entities.Product;
import com.practice.java.security.exceptions.edit.ProductNotFound;
import com.practice.java.security.interfaces.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class Home {

    @Autowired
    private IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) throws ProductNotFound {

        Product product = productService.findById(id);

        if(product == null){
            throw new ProductNotFound("product not found");
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.saveProduct(
                        Product.builder()
                                .id(null)
                                .name(productDto.getName())
                                .price(productDto.getPrice())
                                .amount(productDto.getAmount())
                                .build()
                ));
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateUser(@Valid @RequestBody Product product){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.deleteById(id));
    }
}