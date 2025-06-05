package com.practice.java.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ProductDto implements Serializable {

    @NotBlank(message = "The name can't be null")
    private String name;

    @NotNull(message = "The price is required")
    @PositiveOrZero(message = "The price must be zero or positive")
    private Double price;

    @Positive(message = "The price must be greater than zero")
    private Integer amount;

}
