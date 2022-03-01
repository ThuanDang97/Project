package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.http.request.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {


    //      List<Customer> getListCustomer();
    Page<Customer> getListCustomer(Pageable pageable);


    Page<Customer> searchCustomer(Pageable pageable , String username, String status,
                                  String address, String dateBirthFrom, String dateBirthTo);


    void deleteCustomer(Integer customerId);

    Customer getCustomerByUsername(String username);

    void save(Customer customer);

    boolean checkEmail(String email);

    void createCustomer(CustomerRequest customerRequest);

    void updateCustomer(CustomerRequest customerRequest, Integer id);

    CustomerRequest findById(Integer id);

    Customer findCusById(Integer id);

    Customer findByAccount(String account);
}

