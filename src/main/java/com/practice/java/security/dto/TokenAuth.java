package com.practice.java.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class TokenAuth {

    private String name;

    private String token;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date date;

    @PostConstruct
    public void generatingDate(){
        this.date = new Date(System.currentTimeMillis());
    }
}
