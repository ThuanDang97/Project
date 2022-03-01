package com.example.demo.entity;

public class DataForTopAccount {
    private String name;
    private int money;
    private int time;

    public DataForTopAccount(String name, int money, int time) {
        this.name = name;
        this.money = money;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
