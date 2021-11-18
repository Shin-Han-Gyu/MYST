package com.example.demo.utils.exceptionhandler;

import com.example.demo.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingAdvice {

    @ExceptionHandler
    public ResponseEntity notFoundHandler(NotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
    }
}
