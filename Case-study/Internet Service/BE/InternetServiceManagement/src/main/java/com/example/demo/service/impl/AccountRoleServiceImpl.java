package com.example.demo.service.impl;


import com.example.demo.entity.AccountRole;
import com.example.demo.repository.AccountRoleRepository;
import com.example.demo.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {
    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Override
    public void save(AccountRole accountRole) {
        accountRoleRepository.save(accountRole);
    }
}