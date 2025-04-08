package com.spring.components;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.ACCEPTED,reason = "exception thrown")
public class CustomException2 extends  RuntimeException{
    HttpStatus httpStatus;


    public CustomException2(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus=httpStatus;
    }


    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }
}
