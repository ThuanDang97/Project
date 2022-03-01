package com.example.demo.controller;

import com.example.demo.entity.Pay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentRestController_SearchPayment {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PaymentController paymentController;

    @Test
    public void searchPayment_7() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/payment/search", "null"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void searchPayment_8() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/payment/search", " "))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void searchPayment_9() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/payment/search", "dat@gmail.com"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void searchPayment_10() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/payment/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void searchPayment_11() {
        ResponseEntity<Page<Pay>> responseEntity
                = this.paymentController.searchPayment("a@gmail.com", PageRequest.of(0,2));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
    }
}
