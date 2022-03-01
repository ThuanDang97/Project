package com.example.demo.service.impl;

import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManufacturerComputerRepository;
import com.example.demo.service.ManufacturerComputerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ManufacturerComputerServiceImpl implements ManufacturerComputerService {

    @Autowired
    ManufacturerComputerRepository manufacturerComputerRepository;

    @Override
    public List<Manufacturer> findAllManufacturer() {
        return manufacturerComputerRepository.findAll();
    }
}
