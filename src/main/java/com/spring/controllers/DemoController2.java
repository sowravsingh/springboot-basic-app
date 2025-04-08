package com.spring.controllers;

import com.spring.serivces.OnlineOrders;
import com.spring.serivces.Order;
import com.spring.serivces.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/route")
public class DemoController2 {


    @Autowired
    private Order order;

    @Autowired
    private User user;

    public DemoController2(){
        System.out.println("initaializing second controller");
    }

    @GetMapping("/getUserByID")
    public ResponseEntity<String> getUserDetailsById(@RequestParam(name ="id") int id){
        System.out.println(" thread running in controller "+Thread.currentThread().getName());
        CompletableFuture<Integer> value = user.getUserByUserID(id);
        System.out.println("calling async was done ");
        try {
            System.out.println("value is "+value.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("user details fetched for id "+id);
    }

    @GetMapping("/getUserByName/{name}/{id}")
    public ResponseEntity<String> getUserDetailsByName(@PathVariable(name ="name") String name, @PathVariable(name="id") int id){
        System.out.println("fetched user details for name "+name +"with id "+id);
        user.getUerDetailsByNameId_V2(name,id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("user details fetched for name "+name+" with id "+id);
    }

}
