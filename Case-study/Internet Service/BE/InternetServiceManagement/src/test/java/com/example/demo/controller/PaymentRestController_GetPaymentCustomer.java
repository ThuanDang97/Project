package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentRestController_GetPaymentCustomer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void GetPaymentCustomer_1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/payment/payCustomer/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void GetPaymentCustomer_2() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/payment/payCustomer/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void GetPaymentCustomer_3() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/payment/payCustomer/{id}", 99))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void GetPaymentCustomer_4() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/payment/payCustomer/{id}", 1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.totalPayment").value(200000))
                .andExpect(jsonPath("$.order.id").value(1));
    }
}
