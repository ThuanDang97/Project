package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Exchange;
import com.example.demo.entity.Pay;
import com.example.demo.entity.Rate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PayService {
    List<Pay> findALl();
    Page<Pay> findAll(Pageable pageable);
    Pay findById(Integer id);
    void save(Pay pay);
    boolean pay(Integer id);
    Page<Pay> search(Pageable pageable, String searchName);
    void saveALl(List<Pay> pays);
    Page<Pay> getListPayment(Pageable pageable);
    Pay payUser(Customer customer);
    Exchange calculatorExchange(int totalMoney,Pay pay);
    Double currencyExchange(Integer vnd) throws Exception;
}
