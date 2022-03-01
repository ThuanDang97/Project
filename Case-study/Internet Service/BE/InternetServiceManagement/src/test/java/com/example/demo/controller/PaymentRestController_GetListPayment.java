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
public class PaymentRestController_GetListPayment {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PaymentController paymentController;

    @Test
    public void testGetListPayment_5() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/payment/list")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetListStudent_6() throws Exception {
        ResponseEntity<Page<Pay>> responseEntity
                = this.paymentController.getListPayment(PageRequest.of(0,3));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(1, responseEntity.getBody().getTotalElements());
    }
}
