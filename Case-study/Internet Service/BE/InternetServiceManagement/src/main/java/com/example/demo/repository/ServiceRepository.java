package com.example.demo.repository;

import com.example.demo.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {
    @Query("SELECT s FROM Service s WHERE s.serviceId like %:searchName% or s.serviceName like %:searchName%  " +
            "or s.quantity = :searchName  or s.unit like %:searchName% " +
            "or s.prices = :searchName ")
    Page<Service> search(Pageable pageable, @Param("searchName") String searchName);
}
