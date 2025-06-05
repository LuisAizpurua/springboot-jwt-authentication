package com.practice.java.security.exceptions;

import com.practice.java.security.exceptions.edit.ProductNotFound;
import com.practice.java.security.exceptions.edit.UserError;
import com.practice.java.security.exceptions.other.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Collections;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler({UserError.class, ProductNotFound.class})
    public ResponseEntity<ApiResponse> userAdvice(
            HttpServletRequest request,
            Exception ex){
        ApiResponse apiResponse = ApiResponse.builder()
                .backendMessage( Collections.singletonMap("message",ex.getMessage()) )
                .url(request.getRequestURI())
                .message(ex.getMessage())
                .method(request.getMethod())
                .build();

        return responseEntity(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse> notValid(
            HttpServletRequest request,
            MethodArgumentNotValidException ex){

       ApiResponse response = ApiResponse.builder()
                .method(request.getMethod())
                .message(ex.getMessage())
                .url(request.getRequestURI())
                .build();

        ex.getBindingResult().getFieldErrors().forEach(field->{
            response.getBackendMessage().put(field.getField(), field.getDefaultMessage());
        });

        return responseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiResponse> deniedException(
            HttpServletRequest request,
            AccessDeniedException ex){

        ApiResponse apiResponse =ApiResponse.builder()
                .backendMessage(  Collections.singletonMap("message","You do not have permission to perform this action.") )
                .url(request.getRequestURI())
                .message("Access Denied")
                .method(request.getMethod())
                .build();

        return responseEntity(apiResponse,HttpStatus.FORBIDDEN);
    }
    private ResponseEntity<ApiResponse> responseEntity(ApiResponse apiResponse, HttpStatus status){
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiResponse);
    }
}
