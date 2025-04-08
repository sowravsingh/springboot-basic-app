package com.spring.serivces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Qualifier("offlineOrder")
//@Component
public class OfflineOrders implements Order{
    @Override
    public void printOrderType() {
        System.out.println("initialized offline orders class");
    }
}
