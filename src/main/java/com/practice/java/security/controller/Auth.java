package com.practice.java.security.controller;

import com.practice.java.security.dto.TokenAuth;
import com.practice.java.security.dto.UserAuth;
import com.practice.java.security.dto.UserDto;
import com.practice.java.security.entities.User;
import com.practice.java.security.exceptions.edit.UserError;
import com.practice.java.security.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Auth {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenAuth> register(@Valid @RequestBody UserDto userDto){
        TokenAuth tokenAuth = authService.registerUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tokenAuth);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String,String>> authenticate(@Valid @RequestBody UserAuth userAuth){
        String token = authService.authenticateUser(userAuth);

        if(token == null){
            return  ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Collections.singletonMap("error","not authenticated"));
        }

        return ResponseEntity.status(HttpStatus.OK.value())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Collections.singletonMap("token", token));
    }

    @PostMapping("/validate/{jwt}")
    public ResponseEntity<Boolean> validate(@PathVariable String jwt){
        boolean isValidate = authService.validateToken(jwt);
        return ResponseEntity
                .ok(isValidate);
    }

    @GetMapping("/logged")
    public ResponseEntity<User> loggedInUser() throws UserError {

       User user = authService.loggedIn();

       if(user == null){
           throw new UserError("User not authenticated in the system");
       }

       return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(user);
    }
}
