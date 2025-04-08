package com.spring.serivces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Qualifier("onlineOrder")
//@Component
public class OnlineOrders implements Order {
    @Override
    public void printOrderType() {
        System.out.println("online Order class was initialized");
    }
}
