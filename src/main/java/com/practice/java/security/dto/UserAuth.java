package com.practice.java.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserAuth implements Serializable {

    @NotBlank(message = "The name can't be null")
    private String password;

    @NotBlank(message = "The name can't be null")
    private String username;
}
