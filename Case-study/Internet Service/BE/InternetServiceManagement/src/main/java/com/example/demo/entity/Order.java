package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_hour")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    @JsonIgnore
    private Customer customer;


    @ManyToOne(targetEntity = Computer.class)
    @JoinColumn(name = "computerId", referencedColumnName = "computerId")
    @JsonIgnore
    private Computer computer;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonBackReference
    private Pay pay;

    private String startTime;
    private String endTime;
    private int usageTime;
    private Boolean status;
    public Order() {
    }

    public Order(Customer customer, Computer computer, String startTime, int usageTime) {
        this.customer = customer;
        this.computer = computer;
        this.startTime = startTime;
        this.usageTime = usageTime;
    }

    public Order(Customer customer, String startTime) {
        this.customer = customer;
        this.startTime = startTime;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(int usageTime) {
        this.usageTime = usageTime;
    }
}
