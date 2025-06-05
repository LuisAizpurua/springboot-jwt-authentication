package com.practice.java.security.exceptions.edit;

import lombok.Data;

@Data
public class UserError extends Exception{

    private final String name;

    public UserError(String message){
        this(message, "");
    }

    public UserError(String message, String name){
        super(message);
        this.name = name;
    }

}
