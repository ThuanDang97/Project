package com.example.demo.service.impl;

import com.example.demo.repository.ServiceRepository;
import com.example.demo.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;
  
    @Override
    public void save(com.example.demo.entity.Service service) {
        this.serviceRepository.save(service);
    }

    @Override
    public com.example.demo.entity.Service findById(String serviceId) {
        return this.serviceRepository.findById(serviceId).orElse(null);
    }

    @Override
    public Page<com.example.demo.entity.Service> findAllService(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public com.example.demo.entity.Service findServiceById(String serviceId) {
        return (com.example.demo.entity.Service) serviceRepository.findById(serviceId).orElse(null);
    }

    @Override
    public void deleteService(String serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    @Override
    public Page<com.example.demo.entity.Service> search(Pageable pageable, String searchName) {
        return serviceRepository.search(pageable,searchName);
    }

    @Override
    public void deleteAllService() {
        serviceRepository.deleteAll();
    }

    @Override
    public List<com.example.demo.entity.Service> findAll() {
        return serviceRepository.findAll();
    }
}
