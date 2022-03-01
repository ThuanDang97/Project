package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    private String userName;
    @Column(length = 255)
    private String password;

    @OneToOne(mappedBy = "account" ,cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(mappedBy = "account" ,cascade = CascadeType.ALL)
    private Employee employee;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<AccountRole> accountRoles;

    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public Customer getCustomer() {
       return customer;
   }

   public void setCustomer(Customer customer) {
       this.customer = customer;
   }

   public Employee getEmployee() {
       return employee;
   }

   public void setEmployee(Employee employee) {
       this.employee = employee;
   }

    public Set<AccountRole> getAccountRoles() {
        return accountRoles;
    }
    public void setAccountRoles(Set<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }
}
