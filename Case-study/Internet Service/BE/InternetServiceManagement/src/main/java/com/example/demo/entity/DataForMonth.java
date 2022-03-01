package com.example.demo.entity;

public class DataForMonth {
    private String nameMonth;
    private Integer totalMoneyService;
    private Integer totalMoneyComputer;

    public DataForMonth(String nameMonth, Integer totalMoneyService, Integer totalMoneyComputer) {
        this.nameMonth = nameMonth;
        this.totalMoneyService = totalMoneyService;
        this.totalMoneyComputer = totalMoneyComputer;
    }

    public String getNameMonth() {
        return nameMonth;
    }

    public void setNameMonth(String nameMonth) {
        this.nameMonth = nameMonth;
    }

    public Integer getTotalMoneyService() {
        return totalMoneyService;
    }

    public void setTotalMoneyService(Integer totalMoneyService) {
        this.totalMoneyService = totalMoneyService;
    }

    public Integer getTotalMoneyComputer() {
        return totalMoneyComputer;
    }

    public void setTotalMoneyComputer(Integer totalMoneyComputer) {
        this.totalMoneyComputer = totalMoneyComputer;
    }
}
