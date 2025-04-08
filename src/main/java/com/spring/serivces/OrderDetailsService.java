package com.spring.serivces;

import com.spring.Entities.OrderDetails;
import com.spring.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public void saveOrderDetails(OrderDetails orderDetails){
        orderRepository.save(orderDetails);
    }

    public OrderDetails getOrderDetailsById(Long id){
        return orderRepository.findById(id).get();
    }

    public void testMultipleEntityManagers(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        OrderDetails orderDetails = entityManager.find(OrderDetails.class, 1L);
        System.out.println("order details found by first entity manager is "+orderDetails);
        OrderDetails orderDetails2 = entityManager.find(OrderDetails.class, 1L);
        System.out.println("from first entity manager second time querying "+orderDetails2);
        entityManager.close();

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        OrderDetails orderDetails3 = entityManager2.find(OrderDetails.class, 1L);
        System.out.println("order details found by second entity manager is "+orderDetails3);
        OrderDetails orderDetails4 = entityManager2.find(OrderDetails.class, 1L);
        System.out.println("from first entity manager second time querying "+orderDetails4);
        entityManager.close();

    }

}
