package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value="select customer.* from customer " +
            "right join order_hour on customer.customer_id = order_hour.customer_id " +
            "where (order_hour.start_time between :startTime and :endTime) and (order_hour.end_time between :startTime and :endTime) " +
            "group by order_hour.customer_id " +
            " union " +
            "select customer.* from customer " +
            "right join order_service on customer.customer_id = order_service.customer_id " +
            "where (order_service.order_date between :startTime and :endTime) " +
            "group by order_service.customer_id", nativeQuery = true)
    List<Customer> findAllInStartTimeToEndTime(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    @Query(value= "Select * from customer c where c.username = ?1", nativeQuery=true)
    Customer getCustomerByUsername(String username);

    @Query(value = "select * from customer " , nativeQuery = true)
    Page<Customer> getListCustomer(Pageable pageable);


    @Query(value = "SELECT customer.*,account.username" +
            "   FROM `customer` " +
            "join `account` on `customer`.account_username = `customer`.username " +
            "where (account.username like %?1% or account.username is null ) and customer.status like %?2% and (customer.address like %?3% or is null ) and (customer.date_of_birth between ?4 and ?5)", nativeQuery = true)
    Page<Customer> searchCustomer(Pageable pageable, String username, String status, String address, String dateBirthFrom, String dateBirthTo);


    boolean existsByEmail(String email);

    Customer findByAccount_UserName(String account);
}
