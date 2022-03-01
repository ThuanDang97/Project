package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.OrderService;
import com.example.demo.entity.OrderServiceDTO;
import com.example.demo.entity.Pay;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderServiceRepository;
import com.example.demo.repository.PayRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.service.OrderServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceServiceImpl implements OrderServiceService {
    @Autowired
    private OrderServiceRepository orderServiceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PayRepository payRepository;

    @Override
    public OrderService findById(Integer id) {
        return orderServiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderService> createOrderService(OrderServiceDTO orderServiceDTO) {
        OrderService orderService = toEntity(orderServiceDTO);
        Customer customer = customerRepository.findById(orderServiceDTO.getCustomer()).orElse(null);
        Pay pay = payRepository.findById(orderServiceDTO.getPay()).orElse(null);
        com.example.demo.entity.Service service = serviceRepository.findById(orderServiceDTO.getService()).orElse(null);

        orderService.setCustomer(customer);
        orderService.setPay(pay);
        orderService.setService(service);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String date1= dateFormat.format(date);
        orderService.setOderDate(date1);
        orderService.setUnit(service.getUnit());

        if (orderService.getQuantity() <= service.getQuantity()){
            int totalMoney = orderService.getQuantity() * service.getPrices();
            orderService.setTotalMoney(totalMoney);
            orderServiceRepository.save(orderService);

            int updateQuantity = service.getQuantity() - orderService.getQuantity();
            service.setQuantity(updateQuantity);
            serviceRepository.save(service);
        }
        return this.orderServiceRepository.findAll();
    }

    @Override
    public List<OrderService> findAll() {
        return orderServiceRepository.findAll();
    }


    private OrderServiceDTO toDTO(OrderService orderService){
        OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
        orderServiceDTO.setId(orderService.getId());
        orderServiceDTO.setQuantity(orderService.getQuantity());
        orderServiceDTO.setUnit(orderService.getUnit());
        orderServiceDTO.setTotalMoney(orderService.getTotalMoney());
        orderServiceDTO.setOderDate(orderService.getOderDate());
        orderServiceDTO.setStatus(orderService.isStatus());
        orderServiceDTO.setCustomer(orderService.getCustomer().getCustomerId());
        orderServiceDTO.setService(orderService.getService().getServiceId());
        orderServiceDTO.setPay(orderService.getPay().getId());
        return orderServiceDTO;
    }

    private OrderService toEntity(OrderServiceDTO orderServiceDTO){
        OrderService orderService = new OrderService();
        if (orderServiceDTO.getId() != null) {
            Optional<OrderService> orderService1 = orderServiceRepository.findById(orderServiceDTO.getId());
            if (orderService1.isPresent()) {
                orderService = orderService1.get();
            }
        }
        orderService.setQuantity(orderServiceDTO.getQuantity());
        orderService.setUnit(orderServiceDTO.getUnit());
        orderService.setTotalMoney(orderServiceDTO.getTotalMoney());
        orderService.setOderDate(orderServiceDTO.getOderDate());
        orderService.setStatus(orderServiceDTO.getStatus());

        return orderService;
    }
}
