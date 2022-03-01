package codegym.vn.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerController_deleteCustomer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDeleteCustomer_25() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deleteCustomer/{id}","null"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void testDeleteCustomer_26() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deleteCustomer/{id}"," "))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void testDeleteCustomer_27() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deleteCustomer/{id}","123"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
    @Test
    public void testDeleteCustomer_28() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/customer/deleteCustomer/{id}","KH-0001"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }
}
