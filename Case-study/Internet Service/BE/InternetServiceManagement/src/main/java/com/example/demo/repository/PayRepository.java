package com.example.demo.repository;

import com.example.demo.entity.Pay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PayRepository extends JpaRepository<Pay,Integer> {
    @Query("SELECT p FROM Pay p WHERE p.order.customer.account.userName like %:searchName% or p.order.customer.email like %:searchName%")
    Page<Pay> search(Pageable pageable, @Param("searchName") String searchName);


}
