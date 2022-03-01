package com.example.demo.service.impl;

import com.example.demo.entity.Computer;
import com.example.demo.repository.ComputerRepository;
import com.example.demo.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    ComputerRepository computerRepository;


    @Override
    public Page<Computer> finAll(Pageable pageable) {
        return computerRepository.findAll(pageable);
    }


    @Override
    public Computer findById(String id) {
        return computerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        computerRepository.deleteById(id);
    }

    @Override
    public Page<Computer> search(String id, String computerLocation, String computerStartUsedFrom, String computerStartUsedTo,
                                 String type, String status, Pageable pageable) {
        return computerRepository.advancedSearchComputer(id, computerLocation, computerStartUsedFrom, computerStartUsedTo, type, status, pageable);
    }

    public void save(Computer computer) {
        computerRepository.save(computer);
    }

    @Override
    public void update(Computer computer) {
        computerRepository.save(computer);
    }

    @Override
    public Computer findByIpHost(String ip) {
        return computerRepository.findByComputerIpLocal(ip);
    }

}
