package com.example.demo.service;

import com.example.demo.entity.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComputerService {


    Page<Computer> finAll(Pageable pageable);

    Computer findById(String id);

    void delete (String id);

    Page<Computer> search(String id, String computerLocation, String computerStartUsedFrom, String computerStartUsedTo, String type, String status,Pageable pageable);

    void save(Computer computer);

    void update(Computer computer);

    Computer findByIpHost(String ip);

}
