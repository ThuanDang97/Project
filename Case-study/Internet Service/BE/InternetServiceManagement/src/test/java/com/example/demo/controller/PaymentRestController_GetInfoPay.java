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
public class PaymentRestController_GetInfoPay {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void GetInfoPay_1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/payment/pay/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void GetInfoPay_2() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/payment/pay/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void GetInfoPay_3() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/payment/pay/{id}", 99))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void GetInfoPay_4() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/payment/pay/{id}", 1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value("false"))
                .andExpect(jsonPath("$.totalPayment").value("200000"))
                .andExpect(jsonPath("$.order.id").value(1));
    }
}
