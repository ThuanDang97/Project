package com.example.demo.service;

import com.example.demo.entity.OrderService;
import com.example.demo.entity.OrderServiceDTO;

import java.util.List;

public interface OrderServiceService {
    OrderService findById(Integer id);

    List<OrderService> createOrderService(OrderServiceDTO orderServiceDTO);

    List<OrderService> findAll();

}
