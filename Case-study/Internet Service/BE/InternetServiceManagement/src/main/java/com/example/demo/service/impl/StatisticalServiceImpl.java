package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderServiceRepository;
import com.example.demo.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

@Service
public class StatisticalServiceImpl implements StatisticalService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<DataForComputer> findAllInStartTimeToEndTime(String startTime, String endTime) {
        List<DataForComputer> list = new ArrayList<>();
        Date startDate = null;
        Date endDate = null;
        try{
            startDate = format.parse(startTime);
            endDate = format.parse(endTime);
        }catch (ParseException e){
            return null;
        }
        List<Order> listOrder = this.orderRepository.findAllInStartTimeToEndTime(startDate, endDate);
        if(listOrder.isEmpty()){
            return null;
        }
        Map<String, Integer> map = new TreeMap<>();
        for (Order order : listOrder){
            if(order.isStatus()) {
                if (map.containsKey(order.getComputer().getComputerId())) {
                    int total = map.get(order.getComputer().getComputerId());
                    map.put(order.getComputer().getComputerId(), (total + order.getUsageTime()));
                } else {
                    map.put(order.getComputer().getComputerId(), order.getUsageTime());
                }
            }
        }
        Set<String> set = map.keySet();
        for (String key : set){
            list.add(new DataForComputer(key, map.get(key)));
        }
        return list;
    }

    @Override
    public List<DataForMonth> getDataByMonth(String startTime, String endTime) {
        List<DataForMonth> listDataForMonth = new ArrayList<>();
        Date startDate = null;
        Date endDate = null;
        YearMonth yearMonth = null;
        try{
            startDate = format.parse(startTime);
            endDate = format.parse(endTime);
        }catch (ParseException e) {
            return null;
        }
        if((startDate.getMonth() == endDate.getMonth()) && (startDate.getYear() == endDate.getYear())){
            listDataForMonth.add(this.calculateTotalMoney(startDate, endDate));
        }else{
            while(true){
                yearMonth = YearMonth.of(startDate.getMonth()+1900, startDate.getMonth()+1);
                Date temp = (Date) startDate.clone();
                temp.setDate(yearMonth.lengthOfMonth());
                listDataForMonth.add(this.calculateTotalMoney(startDate, temp));
                startDate.setMonth(startDate.getMonth()+1);
                startDate.setDate(1);
                if((startDate.getMonth() == endDate.getMonth() && (startDate.getYear()==endDate.getYear()))){
                    listDataForMonth.add(this.calculateTotalMoney(startDate, endDate));
                    break;
                }
            }
        }
        return listDataForMonth;
    }

    @Override
    public List<DataForTopAccount> getDataByAccount(String startTime, String endTime) {
        List<DataForTopAccount> list = new ArrayList<>();
        Date startDate = null;
        Date endDate = null;
        try{
            startDate = format.parse(startTime);
            endDate = format.parse(endTime);
        }catch (ParseException e) {
            return null;
        }
        List<Customer> listCustomer = this.customerRepository.findAllInStartTimeToEndTime(startDate, endDate);
        if(listCustomer == null || listCustomer.isEmpty()){
            return null;
        }
        Map<Integer, Customer> mapCustomer = new TreeMap<>();
        for(Customer customer : listCustomer){
            mapCustomer.put(customer.getCustomerId(), customer);
        }
        Set<Integer> setCustomer = mapCustomer.keySet();
        Map<Integer, Integer> mapOrder = new TreeMap<>();
        Map<Integer, Integer> mapOrderService = new TreeMap<>();
        for(Integer key : setCustomer){
            if(mapCustomer.get(key).getOrders() != null){
                for(Order order : mapCustomer.get(key).getOrders()){
                    if(order.isStatus()) {
                        if (mapOrder.containsKey(order.getCustomer().getCustomerId())) {
                            int temp = order.getUsageTime();
                            mapOrder.put(order.getCustomer().getCustomerId(),
                                    mapOrder.get(order.getCustomer().getCustomerId()) + temp);
                        } else {
                            mapOrder.put(order.getCustomer().getCustomerId(), order.getUsageTime());
                        }
                    }
                }
            }
            if(mapCustomer.get(key).getOrderServices() != null){
                for(OrderService orderService : mapCustomer.get(key).getOrderServices()){
                    if(orderService.isStatus()) {
                        if (mapOrderService.containsKey(orderService.getCustomer().getCustomerId())) {
                            int temp = orderService.getTotalMoney();
                            mapOrderService.put(orderService.getCustomer().getCustomerId(),
                                    mapOrderService.get(orderService.getCustomer().getCustomerId()) + temp);
                        } else {
                            mapOrderService.put(orderService.getCustomer().getCustomerId(),
                                    orderService.getTotalMoney());
                        }
                    }
                }
            }
        }
        Set<Integer> setOrder = mapOrder.keySet();
        Set<Integer> setOrderService = mapOrderService.keySet();
        if(setOrder.size() > setOrderService.size()){
            for(Integer keyOrder : setOrder){
                list.add(new DataForTopAccount(keyOrder+"",
                        mapOrderService.get(keyOrder)==null?0:mapOrderService.get(keyOrder),
                        mapOrder.get(keyOrder)));
            }
        }else{
            for(Integer keyOrderService : setOrderService){
                list.add(new DataForTopAccount(keyOrderService+"", mapOrderService.get(keyOrderService),
                        mapOrder.get(keyOrderService)==null?0:mapOrder.get(keyOrderService)));
            }
        }
        list.sort((DataForTopAccount data1, DataForTopAccount data2) -> {
            if(data1.getMoney() != data2.getMoney()){
                return Integer.compare(data2.getMoney(), data1.getMoney());
            }else{
                return Integer.compare(data2.getTime(), data1.getTime());
            }
        });
        return list;
    }

    private DataForMonth calculateTotalMoney(Date startDate, Date endDate){
        SimpleDateFormat month = new SimpleDateFormat("MM");
        List<OrderService> listOrderService = this.orderServiceRepository.
                findAllInStartTimeToEndTime(startDate, endDate);
        List<Order> listOrder = this.orderRepository.findAllInStartTimeToEndTime(startDate, endDate);
        int totalMoneyComputer = 0;
        int totalMoneyService = 0;
        for(Order order : listOrder){
            if(order.isStatus()) {
                switch (order.getComputer().getType().getTypeId()) {
                    case 1:
                        totalMoneyComputer += order.getUsageTime() * 10000;
                        break;
                    case 2:
                        totalMoneyComputer += order.getUsageTime() * 5000;
                        break;
                }
            }
        }
        for(OrderService order : listOrderService){
            totalMoneyService += order.getTotalMoney();
        }
        return new DataForMonth(month.format(startDate),
                totalMoneyService, totalMoneyComputer);
    }


}
