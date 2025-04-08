package com.spring.serivces;


import com.spring.components.CustomAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.CompletableFuture;

@Component
public class User {


    @Autowired
    private Address address;


    @Autowired
    private PlatformTransactionManager userPlatformTransactionManager;

    @Autowired
    private TransactionTemplate transactionTemplate;


//    @Autowired
//    public void setAddress(Address address){
//        this.address=address;
//    }

    public User(){
        System.out.println("creating user bean");
    }


    @Async
    @Transactional(transactionManager = "getUserSpecificTransactionManager")
    public CompletableFuture<Integer> getUserByUserID(int id){
        System.out.println("doing some db acivities");
        System.out.println("current transaction in main method is "+TransactionSynchronizationManager.getCurrentTransactionName());
        try {
            System.out.println("current thread name is "+Thread.currentThread().getName());
          //  System.out.println(5/0);
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        nestedMethod();
        return CompletableFuture.completedFuture(6);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void nestedMethod(){
        System.out.println("current transaction in nested method is "+TransactionSynchronizationManager.getCurrentTransactionName());
    }


    public void getUerDetailsByNameId(String name , int id){
        TransactionStatus status= userPlatformTransactionManager.getTransaction(null);
        try{
            System.out.println(" fetching db data");
        }catch (Exception e){
            userPlatformTransactionManager.rollback(status);
        }
        userPlatformTransactionManager.commit(status);
    }

    public void getUerDetailsByNameId_V2(String name , int id){
        TransactionCallback<TransactionStatus> callback = (TransactionStatus status) ->{
            System.out.println(" running business logic");
            return status;
        };

        TransactionStatus status= transactionTemplate.execute(callback);
    }



    @CustomAnnotation(intKey = 2024)
    public void getUserDetailsV2(){
        System.out.println("inside user service class");
    }

//    @PostConstruct
//    public void postConstructMethod(){
//        System.out.println("user object initialisation was done with hashcode "+);
//    }

}

