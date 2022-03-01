package com.example.demo.controller;

import com.example.demo.entity.AccountCustomer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerRestController_registerCustomer {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void register_1() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setFullName(null);
        accountCustomer.setDateOfBirth(null);
        accountCustomer.setAddress(null);
        accountCustomer.setEmail(null);
        accountCustomer.setPhone(null);
        accountCustomer.setIdCard(null);
        accountCustomer.setUserName(null);
        accountCustomer.setPassword(null);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_2() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setFullName("");
        accountCustomer.setDateOfBirth("");
        accountCustomer.setAddress("");
        accountCustomer.setEmail("");
        accountCustomer.setPhone("");
        accountCustomer.setIdCard("");
        accountCustomer.setUserName("");
        accountCustomer.setPassword("");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_3() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc");
        accountCustomer.setPhone("0916309250");
        accountCustomer.setIdCard("123456789");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc12345");
        accountCustomer.setPassword("abc123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError()  );
    }

    @Test
    public void register_4() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc@gmail.com");
        accountCustomer.setPhone("0916309250");
        accountCustomer.setIdCard("1234");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc12345");
        accountCustomer.setPassword("abc123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_5() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc@gmail.com");
        accountCustomer.setPhone("09163092");
        accountCustomer.setIdCard("123456789");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc12345");
        accountCustomer.setPassword("abc123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_6() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc@gmail.com");
        accountCustomer.setPhone("0916309250");
        accountCustomer.setIdCard("123456789");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc1");
        accountCustomer.setPassword("abc123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_7() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc@gmail.com");
        accountCustomer.setPhone("0916309250");
        accountCustomer.setIdCard("123456789");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc12345678912354");
        accountCustomer.setPassword("abc123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_8() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc@gmail.com");
        accountCustomer.setPhone("0916309250");
        accountCustomer.setIdCard("123456789");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc12345");
        accountCustomer.setPassword("abc1");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_9() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc@gmail.com");
        accountCustomer.setPhone("0916309250");
        accountCustomer.setIdCard("123456789");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc12345");
        accountCustomer.setPassword("abc1234561515545465454");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_10() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc@gmail.com");
        accountCustomer.setPhone("0916309250");
        accountCustomer.setIdCard("123456789");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc12345");
        accountCustomer.setPassword("abc123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void register_11() throws Exception {
        AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setCustomerId(2);
        accountCustomer.setFullName("Ngo Hien Nhan");
        accountCustomer.setDateOfBirth("11-06-1999");
        accountCustomer.setAddress("da nang");
        accountCustomer.setEmail("abc@gmail.com");
        accountCustomer.setPhone("0916309250");
        accountCustomer.setIdCard("123456789");
        accountCustomer.setStatus(true);
        accountCustomer.setUserName("abc12345");
        accountCustomer.setPassword("abc123456");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customer/register")
                        .content(this.objectMapper.writeValueAsString(accountCustomer))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
