package com.example.demo.service.impl;

import com.example.demo.entity.Type;
import com.example.demo.repository.TypeComputerRepository;
import com.example.demo.service.TypeComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeComputerServiceImpl implements TypeComputerService {
    @Autowired
    TypeComputerRepository typeComputerRepository;
    @Override
    public List<Type> finAllType() {
        return typeComputerRepository.findAll();
    }
}
