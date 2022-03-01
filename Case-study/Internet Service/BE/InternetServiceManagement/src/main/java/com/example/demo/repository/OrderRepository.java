package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("select o from Order o where o.status = true and " +
            "((o.startTime between :startTime and :endTime) and (o.endTime between :startTime and :endTime)) ")
    List<Order> findAllInStartTimeToEndTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
