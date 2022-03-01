package codegym.vn.service;

import codegym.vn.entity.Contract;

import java.util.Date;
import java.util.List;

public interface StatisticService {
    List<Contract> statisticInterest(Date startDate,Date endDate);
    List<Contract> statisticLiquidation(Date startDate,Date endDate);
    List<Contract> statisticExpected(Date startDate,Date endDate);
}
