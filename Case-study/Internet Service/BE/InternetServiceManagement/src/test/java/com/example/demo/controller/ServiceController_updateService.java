package com.example.demo.controller;

import com.example.demo.entity.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceController_updateService {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    private void updateService_19() throws Exception {
        Service service = new Service();
        service.setServiceId("null");
        service.setServiceName("Pepsi");
        service.setQuantity(1);
        service.setUnit("chai");
        service.setPrices(5000);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .patch("/service/update/null")
                                .content(this.objectMapper.writeValueAsString(service))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    private void updateService_20() throws Exception {
        Service service = new Service();
        service.setServiceId("SV0001");
        service.setServiceName("");
        service.setQuantity(1);
        service.setUnit("chai");
        service.setPrices(5000);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .patch("/service/update/SV0001")
                                .content(this.objectMapper.writeValueAsString(service))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    private void updateService_21() throws Exception {
        Service service = new Service();
        service.setServiceId("SV-0001");
        service.setServiceName("Pepsi");
        service.setQuantity(1);
        service.setUnit("chai");
        service.setPrices(5000);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .patch("/service/update/SV0001")
                                .content(this.objectMapper.writeValueAsString(service))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    private void updateService_22() throws Exception {
        Service service = new Service();
        service.setServiceId("SV0001");
        service.setServiceName("Pepsi");
        service.setQuantity(0);
        service.setUnit("chai");
        service.setPrices(5000);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .patch("/service/update/SV0001")
                                .content(this.objectMapper.writeValueAsString(service))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

//    @Test
//    private void updateService_23() throws Exception {
//        Service service = new Service();
//        service.setServiceId("SV0001");
//        service.setServiceName("Pepsi");
//        service.setQuantity(9999999999999);
//        service.setUnit("chai");
//        service.setPrices(5000);
//
//        this.mockMvc
//                .perform(
//                        MockMvcRequestBuilders
//                                .patch("/service/update/SV0001")
//                                .content(this.objectMapper.writeValueAsString(service))
//                                .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }

    @Test
    private void createService_24() throws Exception {
        Service service = new Service();
        service.setServiceId("SV0001");
        service.setServiceName("Pepsi");
        service.setQuantity(1);
        service.setUnit("chai");
        service.setPrices(5000);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .patch("/service/update/SV0001")
                                .content(this.objectMapper.writeValueAsString(service))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
