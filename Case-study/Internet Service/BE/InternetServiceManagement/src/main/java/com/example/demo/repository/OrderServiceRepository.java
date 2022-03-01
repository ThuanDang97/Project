package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderServiceRepository extends JpaRepository<OrderService, Integer> {
    @Query("select o from OrderService o where (o.orderDate between :startTime and :endTime) and (o.status = true) ")
    List<OrderService> findAllInStartTimeToEndTime(@Param("startTime") Date startTime,
                                                   @Param("endTime")Date endTime);
}
