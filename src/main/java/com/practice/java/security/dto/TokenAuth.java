package com.practice.java.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TokenAuth {

    private String name;

    private String token;

    @Builder.Default
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private final Date date = new Date(System.currentTimeMillis());

}
