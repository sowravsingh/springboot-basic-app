package com.spring.components;

import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class CustomAPIResponse extends RepresentationModel<CustomAPIResponse> {

    int statusCode;
    Object response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    String message;

    public CustomAPIResponse(int statusCode, Object response, String message) {
        this.statusCode = statusCode;
        this.response = response;
        this.message = message;
    }

    public CustomAPIResponse(){

    }




}
