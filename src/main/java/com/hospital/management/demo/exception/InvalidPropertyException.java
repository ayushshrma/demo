package com.hospital.management.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPropertyException extends RuntimeException {
    public InvalidPropertyException(String message){
        super(message);
    }
}