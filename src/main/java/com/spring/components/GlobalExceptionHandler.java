package com.spring.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class, NullPointerException.class})
    public ResponseEntity<?> handleCustomException(Exception e){

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(),e.getMessage()+"exception handled at global exception class", new Date());
        return new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }
}
