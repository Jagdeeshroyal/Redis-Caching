package com.jagadeesh.redisexample.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(HumanNotFoundException.class)
    public ResponseEntity<Object> handleHumanNotFoundException(HumanNotFoundException ex){
        log.info("Exception occured: "+ ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
