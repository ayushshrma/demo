package com.hospital.management.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PropertyAccessDeniedException extends RuntimeException {
    public PropertyAccessDeniedException(String message){
        super(message);
    }
}