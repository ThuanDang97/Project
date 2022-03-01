package com.example.demo.service;

import com.example.demo.entity.DataForComputer;
import com.example.demo.entity.DataForMonth;
import com.example.demo.entity.DataForTopAccount;

import java.util.List;

public interface StatisticalService {
    List<DataForComputer> findAllInStartTimeToEndTime(String startTime, String endTime);

    List<DataForMonth> getDataByMonth(String startTime, String endTime);

    List<DataForTopAccount> getDataByAccount(String startTime, String endTime);
}
