package codegym.vn.controller;

import codegym.vn.entity.Contract;
import codegym.vn.service.StatisticService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/statistical")
public class StatisticController {
    @Autowired
    StatisticService statisticService;

    @GetMapping("/statisticInterest")
    public ResponseEntity<List<Contract>> statisticInterest(
            @RequestParam(value = "start", required = false)String start,
            @RequestParam(value="end", required = false)String end) throws ParseException {
        if(start==null || end==null ||start.equals("")||end.equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date startDate=new SimpleDateFormat("dd/MM/yyyy").parse(start);
        Date endDate=new SimpleDateFormat("dd/MM/yyyy").parse(end);
        List<Contract> contractList = statisticService.statisticInterest(startDate,endDate);
        if (contractList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contractList, HttpStatus.OK);
    }

    @GetMapping("/statisticLiquidation")
    public ResponseEntity<List<Contract>> statisticLiquidation(
            @RequestParam(value = "start", required = false)String start,
            @RequestParam(value="end", required = false)String end) throws ParseException {
        if(start==null || end==null ||start.equals("")||end.equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date startDate=new SimpleDateFormat("dd/MM/yyyy").parse(start);
        Date endDate=new SimpleDateFormat("dd/MM/yyyy").parse(end);
        List<Contract> contractList = statisticService.statisticLiquidation(startDate,endDate);
        if (contractList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contractList, HttpStatus.OK);
    }
    @GetMapping("/statisticExpected")
    public ResponseEntity<List<Contract>> statisticExpected(
            @RequestParam(value = "start", required = false)String start,
            @RequestParam(value="end", required = false)String end) throws ParseException {
        if(start==null || end==null ||start.equals("")||end.equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date startDate=new SimpleDateFormat("dd/MM/yyyy").parse(start);
        Date endDate=new SimpleDateFormat("dd/MM/yyyy").parse(end);
        List<Contract> contractList = statisticService.statisticExpected(startDate,endDate);
        if (contractList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contractList, HttpStatus.OK);
    }
}
