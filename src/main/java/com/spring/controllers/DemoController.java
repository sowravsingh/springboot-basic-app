package com.spring.controllers;


import com.spring.components.Job;
import com.spring.serivces.Address;
import com.spring.serivces.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
//@Scope("request")
public class DemoController {

    @Autowired
    private Address address; // Request-scoped dependency

    List<Job> jobs = new ArrayList<>();


    public DemoController(){
        System.out.println("Initializing DemoController");
    }

    @PostConstruct
    public void postConstructMethod(){
        System.out.println("DemoController initialized with controller hashcode: " + this.hashCode() +
                " and address object hashcode: " + address.hashCode());
    }


    @PostMapping("/api/addJobs")
    public void addjobs(@RequestBody Job job){
        jobs.add(job);
    }

    @GetMapping("/api/getJobs")
    public  List<Job> getJobDetails() {
//        System.out.println("API hit with controller hashcode: " + this.hashCode() +
//                " and address object hashcode: " + address.hashCode());
        return jobs;
    }
}
