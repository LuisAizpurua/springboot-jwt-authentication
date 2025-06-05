package com.practice.java.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(precision = 2)
    private Double price;

    private Integer amount;

//    @ManyToMany(mappedBy = "product_id")
//    @JsonIgnore
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private List<User> user_id;
}
