package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.PayRepository;
import com.example.demo.repository.SerivceRepository;
import com.example.demo.service.PayService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PayRepository payRepository;
    @Autowired
    private NetWork netWork;

    @Override
    public List<Pay> findALl() {
        return payRepository.findAll();
    }

    @Override
    public Page<Pay> findAll(Pageable pageable) {
        return payRepository.findAll(pageable);
    }

    @Override
    public Pay findById(Integer id) {
        return payRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Pay pay) {
        payRepository.save(pay);
    }

    @Override
    public boolean pay(Integer id) {
        Pay payCurrent = payRepository.findById(id).orElse(null);
        if (payCurrent==null){
            return false;
        }else {
            payCurrent.setStatus(true);
            for (OrderService orderService:payCurrent.getOrderServices()){
                orderService.setStatus(true);
            }
            payRepository.save(payCurrent);
            return true;
        }

    }

    @Override
    public Page<Pay> search(Pageable pageable, String searchName) {
        return payRepository.search(pageable,searchName);
    }

    @Override
    public void saveALl(List<Pay> pays) {
        this.payRepository.saveAll(pays);
    }
    //-------------------Retrieve Payment By Role Employee/Admin------------------------------------------
    @Override
    public Page<Pay> getListPayment(Pageable pageable) {
        Page<Pay> pays = payRepository.findAll(pageable);
        int totalPayment=0;
        for (Pay pay: pays.getContent()){
            int totalMoney=0;
            for (OrderService orderService: pay.getOrderServices()){
                totalMoney += orderService.getTotalMoney();
            }
            totalPayment = (pay.getOrder().getUsageTime()*20000)+totalMoney;
            pay.setTotalPayment(totalPayment);
        }
        this.payRepository.saveAll(pays.getContent());
        return pays;
    }
    //-------------------Payment By Role User------------------------------------------
    @Override
    public Pay payUser(Customer customer) {
        if (customer.getOrders() != null && !customer.getOrders().isEmpty()){
            for (Order order : customer.getOrders()) {
                if (order.getPay()!=null){
//                    if (!order.getPay().isStatus()) {
                        int totalPayment = 0;
                        int totalMoney = 0;
                        for (OrderService orderService : order.getPay().getOrderServices()) {
                            totalMoney += orderService.getTotalMoney();
                        }
                        totalPayment = totalMoney + order.getUsageTime() * 20000;
                        order.getPay().setTotalPayment(totalPayment);
                        payRepository.save(order.getPay());
                        return order.getPay();
//                    }
                }
            }
        }
        return null;
    }
    //-------------------Calculator exchange money------------------------------------------
    @Override
    public Exchange calculatorExchange(int moneyRecived, Pay pay) {
        int exchangeMoney=0;
        exchangeMoney = moneyRecived - pay.getTotalPayment();
        Exchange exchange = new Exchange(exchangeMoney);
        return exchange;
    }

    @Override
    public Double currencyExchange(Integer vnd) throws Exception {

        List<Rate> allRates = new ArrayList<Rate>();

        String rawJson = netWork.request("https://www.dongabank.com.vn/exchange/export");
        String dataRender = rawJson.substring(1, rawJson.length() - 1);

        JSONObject root = new JSONObject(dataRender);

        JSONArray items = root.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            // the JSON data
            JSONObject jsonPlant = items.getJSONObject(i);
            // Plant object that we will populate from JSON data.
            Rate rate = new Rate();
            String type = jsonPlant.getString("type");
            String imageurl = jsonPlant.getString("imageurl");
            String muatienmat = jsonPlant.getString("muatienmat");
            String muack = jsonPlant.getString("muack");
            String bantienmat = jsonPlant.getString("bantienmat");
            String banck = jsonPlant.getString("banck");

            // populate our DTO with this information,.
            rate.setType(type);
            rate.setImageurl(imageurl);
            rate.setMuatienmat(muatienmat);
            rate.setMuack(muack);
            rate.setBantienmat(bantienmat);
            rate.setBanck(banck);

            // add the populated plant to our collection.
            allRates.add(rate);

        }
        Rate rateTemp = null;
        for (int i = 0; i < allRates.size(); i++) {
            if (allRates.get(i).getType().equals("USD")) {
                rateTemp = allRates.get(i);
            }
        }
        if (rateTemp==null){
            return null;
        }
        Double rate = Double.valueOf(rateTemp.getBanck());
        Double result = Double.valueOf(vnd/rate);
        Double usd = (double) Math.round(result * 100) / 100;
        return usd;
    }
}
