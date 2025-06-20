package com.sanbro.redis.Exceptions;

import com.sanbro.redis.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userAlreadyExists(UserAlreadyExistException ex, HttpServletRequest http){
        ExceptionResponse exceptionResponse = new ExceptionResponse("User exists with mail",ex.getMessage(), HttpStatus.CONFLICT.value(), http.getRequestURI());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.CONFLICT);
    }
}
