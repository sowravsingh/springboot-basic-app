package com.spring.serivces;


import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;


public class MySqlConnection {

    @Value("${name}")
    String userName;

    @Value("${Password}")
    String password;

    public MySqlConnection(){
        System.out.println("mysql connection class object was created");
    }

    @PostConstruct
    public void printCredentials(){
        System.out.println(" user name is "+userName+ " password is "+password);
    }
}
