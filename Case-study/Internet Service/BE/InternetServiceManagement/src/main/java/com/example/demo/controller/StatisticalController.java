package com.example.demo.controller;

import com.example.demo.entity.DataForComputer;
import com.example.demo.entity.DataForMonth;
import com.example.demo.entity.DataForTopAccount;
import com.example.demo.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/statistical")
public class StatisticalController {
    @Autowired
    private StatisticalService statisticalService;

    @GetMapping(value="/view-by-computer")
    public ResponseEntity<List<DataForComputer>> viewByComputer(
            @RequestParam(value = "startTime", required = false)String startTime,
            @RequestParam(value="endTime", required = false)String endTime){
        if(startTime == null || startTime.equals("") || endTime == null || endTime.equals("")){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DataForComputer> list = this.statisticalService.findAllInStartTimeToEndTime(startTime, endTime);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/view-by-month")
    public ResponseEntity<List<DataForMonth>> viewByMonth(
            @RequestParam(value = "startTime", required = false)String startTime,
            @RequestParam(value="endTime", required = false)String endTime){
        if(startTime == null || startTime.equals("") || endTime == null || endTime.equals("")){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DataForMonth> list = this.statisticalService.getDataByMonth(startTime, endTime);
        if(list == null || list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/view-by-account")
    public ResponseEntity<List<DataForTopAccount>> viewAccount(@RequestParam(value = "startTime", required = false)String startTime,
                                                               @RequestParam(value="endTime", required = false)String endTime,
                                                               @RequestParam(value="quarter", required = false)String quarter){
        if(startTime == null || startTime.equals("") || endTime == null || endTime.equals("")){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DataForTopAccount> list = this.statisticalService.getDataByAccount(startTime, endTime);
        if(list == null || list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}


