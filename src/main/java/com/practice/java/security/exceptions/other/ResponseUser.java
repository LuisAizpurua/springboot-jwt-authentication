package com.practice.java.security.exceptions.other;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ResponseUser {

    private String name;
    private String message;
    private Date date;

}
