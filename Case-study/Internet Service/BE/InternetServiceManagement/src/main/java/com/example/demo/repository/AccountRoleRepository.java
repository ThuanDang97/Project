package com.example.demo.repository;

import com.example.demo.entity.AccountRole;
import com.example.demo.entity.AccountRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRoleRepository extends JpaRepository<AccountRole, AccountRoleKey> {
}
