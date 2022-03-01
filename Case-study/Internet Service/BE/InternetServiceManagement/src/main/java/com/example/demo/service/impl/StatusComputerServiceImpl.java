package com.example.demo.service.impl;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusComputerRepository;
import com.example.demo.service.StatusComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusComputerServiceImpl implements StatusComputerService {
    @Autowired
    StatusComputerRepository statusComputerRepository;
    @Override
    public List<Status> finAll() {
        return statusComputerRepository.findAll();
    }
}
