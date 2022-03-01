package com.example.demo.service;

import com.example.demo.entity.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ServiceService {

    void save(Service service);

    Service findById(String serviceId);

    Page<Service> findAllService(Pageable pageable);

    Service findServiceById(String serviceId);

    void deleteService (String serviceId);

    Page<Service> search(Pageable pageable, String searchName);

    void deleteAllService ();

    List<Service> findAll();
}

