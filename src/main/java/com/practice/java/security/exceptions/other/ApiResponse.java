package com.practice.java.security.exceptions.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ApiResponse {

    private Map<String, Object> backendMessage;
    private String url;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date timestamp;
    private String method;
    private String message;

    @PostConstruct
    public void initialData(){
        this.timestamp = new Date(System.currentTimeMillis());
        this.backendMessage = new HashMap<>();
    }
}
