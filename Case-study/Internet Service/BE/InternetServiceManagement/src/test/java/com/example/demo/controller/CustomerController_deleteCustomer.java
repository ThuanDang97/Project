package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerController_deleteCustomer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDeleteCustomer_25() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete/{idCustomer}","null"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void testDeleteCustomer_26() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete/{idCustomer}",""))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void testDeleteCustomer_27() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete/{idCustomer}","8"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteEmployee_28() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/delete/{idCustomer}","9"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }
}

