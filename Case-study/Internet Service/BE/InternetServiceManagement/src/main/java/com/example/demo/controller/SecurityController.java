package com.example.demo.controller;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.entity.*;
import com.example.demo.jwt.JwtResponse;
import com.example.demo.jwt.JwtResponseEmployee;
import com.example.demo.jwt.LoginRequest;
import com.example.demo.repository.OrderRepository;
import com.example.demo.sercurity.AccountDetailsImpl;
import com.example.demo.service.AccountService;
import com.example.demo.service.ComputerService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SecurityController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ComputerService computerService;
    @Autowired
    private OrderRepository orderHourRepository;
    @PostMapping(value = "/api/public/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication;
        Account account = accountService.findByUserName(loginRequest.getUsername());
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            if (accountService.findByUserName(loginRequest.getUsername()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree("{\"error\": \"Tên đăng nhập hoặc mật khẩu không đúng\"}");
                return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
            } else {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree("{\"error\": \"Không tìm thấy tài khoản\"}");
                return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
            }
        }

        InetAddress IP=InetAddress.getLocalHost();
        String ipHost=IP.getHostAddress();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String time=dtf.format(LocalDateTime.now());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(loginRequest.getUsername());
        AccountDetailsImpl userDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByAccount(account.getUserName());
        Employee employee = employeeService.findByAccountName(account.getUserName());
        Computer computer=computerService.findByIpHost(ipHost);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (roles.get(0).equals("ROLE_ADMIN") || roles.get(0).equals("ROLE_EMPLOYEE")) {
            return ResponseEntity.ok(
                    new JwtResponseEmployee(jwt, account, employee, roles)
            );
        }
        Order orderHour=new Order(customer,computer,time,0);
        orderHourRepository.save(orderHour);
        return ResponseEntity.ok(
                new JwtResponse(jwt, account, customer,orderHour, roles)
        );
    }

    @GetMapping("/api/admin/hello")
    public ResponseEntity<?> test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree("{\"success\": \"Hello\"}");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}