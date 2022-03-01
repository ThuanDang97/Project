package codegym.vn.service.impl;

import codegym.vn.entity.Contract;
import codegym.vn.repository.StatisticRepository;
import codegym.vn.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    StatisticRepository statisticRepository;


    @Override
    public List<Contract> statisticInterest(Date startDate, Date endDate) {
        return statisticRepository.statisticInterest(startDate,endDate);
    }

    @Override
    public List<Contract> statisticLiquidation(Date startDate, Date endDate) {
        return statisticRepository.statisticLiquidation(startDate,endDate);
    }

    @Override
    public List<Contract> statisticExpected(Date startDate, Date endDate) {
        return statisticRepository.statisticExpected(startDate,endDate);
    }
}
