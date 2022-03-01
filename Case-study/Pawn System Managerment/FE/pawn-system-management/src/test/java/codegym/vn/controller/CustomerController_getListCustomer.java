package codegym.vn.controller;

import codegym.vn.entity.Customer;
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
public class CustomerController_getListCustomer {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerController customerController;

    @Test
    public void testGetListCustomer_5() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/customer/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetListCustomer_6() {

        ResponseEntity<Page<Customer>> responseEntity
                = this.customerController.getListCustomer(PageRequest.of(0, 5));

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(6, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("KH-0001",
                responseEntity.getBody().getContent().get(0).getCustomerId());
    }

}
