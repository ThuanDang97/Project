package com.example.demo.service;

import com.example.demo.entity.Account;

import java.util.List;

public interface AccountService {
    boolean checkUserName(String userName);

    void save(Account account);
    List<Account> getAll();
    Account findByUserName(String userName);
}
