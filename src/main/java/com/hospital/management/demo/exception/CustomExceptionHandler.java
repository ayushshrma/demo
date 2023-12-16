package com.hospital.management.demo.exception;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(InvalidPropertyException.class)
  public final ResponseEntity<Object> exmException4(Exception ex, WebRequest request) {
    ErrorDetails errorDetails=new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(true));
    return new ResponseEntity(errorDetails, HttpStatus.NOT_ACCEPTABLE);

  }
  @ExceptionHandler(PropertyAlreadyExistsException.class)
  public final ResponseEntity<Object> exmException2(Exception ex, WebRequest request) {
    ErrorDetails errorDetails=new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(true));
    return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(PropertyNotFoundException.class)
  public final ResponseEntity<Object> exmException5(Exception ex, WebRequest request) {
    ErrorDetails errorDetails=new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(true));
    return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);

  }
  @ExceptionHandler(PropertyAccessDeniedException.class)
  public final ResponseEntity<Object> exmException1(Exception ex, WebRequest request) {
    ErrorDetails errorDetails=new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(true));
    return new ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED);

  }
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> exmException(Exception ex, WebRequest request) {
    ErrorDetails errorDetails=new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(true));
    return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}