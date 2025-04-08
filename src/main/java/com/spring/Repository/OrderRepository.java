package com.spring.Repository;

import com.spring.Entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderDetails,Long> {

    // as of now we are using all inb=Built but if u want any other methods we can write them here
}
