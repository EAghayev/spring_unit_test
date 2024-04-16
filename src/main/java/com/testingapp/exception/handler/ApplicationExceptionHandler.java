package com.testingapp.exception.handler;

import com.testingapp.exception.EntityDublicateException;
import com.testingapp.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleException(EntityNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EntityDublicateException.class)
    public ResponseEntity handleException(EntityDublicateException e){
        return ResponseEntity.status(409).build();
    }
}
