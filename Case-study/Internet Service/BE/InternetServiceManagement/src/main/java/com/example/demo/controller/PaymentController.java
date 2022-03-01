package com.example.demo.controller;


import com.example.demo.entity.PayPalClient;
import com.example.demo.entity.*;
import com.example.demo.service.CustomerService;
import com.example.demo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PayService payService;
    @Autowired
    private CustomerService customerService;
    private final PayPalClient payPalClient;
    @Autowired
    PaymentController(PayPalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    //-------------------Retrieve All Payment By Role Employee/Admin--------------------
    @GetMapping(value = "/list")
    public ResponseEntity<Page<Pay>> getListPayment(@PageableDefault(3) Pageable pageable) {
        Page<Pay> pays = payService.getListPayment(pageable);
        if (pays.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pays, HttpStatus.OK);
    }

    //-------------------Retrieve Payment By Role Customer------------------------------------
    @GetMapping(value = "/payCustomer/{id}")
    public ResponseEntity<Pay> getPaymentCustomer(@PathVariable("id") int id) {
        Customer customer = this.customerService.findCusById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Pay pay = this.payService.payUser(customer);
        if (pay==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pay, HttpStatus.OK);
    }

    //-------------------Retrieve Search Payment-------------------------------------------
    @GetMapping(value = "/search")
    public ResponseEntity<Page<Pay>> searchPayment(@RequestParam("searchName") String searchName,
                                                   @PageableDefault(3) Pageable pageable) {
        Page<Pay> searchPay = payService.search(pageable, searchName);
        if (searchPay == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(searchPay,HttpStatus.OK);
    }

    //-------------------Retrieve Single Payment--------------------------------------------
    @GetMapping(value = "/pay/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pay> getInfoPay(@PathVariable("id") int id) {
        Pay pay = payService.findById(id);
        if (pay == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pay, HttpStatus.OK);
    }

    //------------------- Pay Payment--------------------------------------------------------
    @PostMapping(value = "/pay")
    public ResponseEntity<Void> pay(@RequestParam("id") int id) {
        if (payService.pay(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //------------------- Calculator Exchange Money--------------------------------------------------------
    @PostMapping(value = "/pay/exchange")
    public ResponseEntity<Exchange> exchange(@RequestParam("id") int id,@RequestParam("moneyRecived") int moneyRecived){
        Pay payCurrent = this.payService.findById(id);
        if (payCurrent==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Exchange exchange = this.payService.calculatorExchange(moneyRecived,payCurrent);
        return new ResponseEntity<>(exchange,HttpStatus.OK);
    }
    //------------------- Make Payment Paypal--------------------------------------------------------
    @PostMapping(value = "/make/paypal")
    public ResponseEntity<Map<String, Object>>makePayment(@RequestParam("id") int id) throws Exception {
        Pay payCurrent = this.payService.findById(id);
        if (payCurrent==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Double usd = payService.currencyExchange(payCurrent.getTotalPayment());
        return new ResponseEntity<>(payPalClient.createPayment(usd,id),HttpStatus.OK);

    }
    //------------------- Complete Payment Paypal--------------------------------------------------------
    @GetMapping(value = "/complete/paypal")
    public RedirectView completePayment(@RequestParam("id") int id,HttpServletRequest request){
        payPalClient.completePayment(request);
        payService.pay(id);
        RedirectView redirectView = new RedirectView();
        String message = "successfully";
        redirectView.setUrl("http://localhost:4200/payment-customer?message="+message);
        return redirectView;
    }

}

