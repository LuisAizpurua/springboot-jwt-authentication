package com.practice.java.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.java.security.entities.other.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserDto implements Serializable {

    private final String pattern =  "^(?=(?:.*\\d){3,})(?=.*\\.)[a-zA-Z0-9.]{5,15}$";
    private final String msg = "Must be 5-15 characters, include at least 3 digits and 1 dot (.), and only contain letters, numbers, and dots.";

    @NotBlank
    @Size(max = 20)
    private String name;

    @Pattern(regexp = pattern, message = msg)
    private String username;

    @Pattern(regexp = pattern, message = msg)
    private String password;

    @JsonIgnore
    private Role role;
}