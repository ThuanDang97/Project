package codegym.vn.controller;

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
class StatisticInterest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void statisticInterest_7() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/statisticInterest?&")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    void statisticInterest_8() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/statisticInterest?start=&end=")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void statisticInterest_10() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/statisticInterest?start=01/01/2021&end=30/02/2021")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void statisticInterest_11() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/statisticInterest?start=01/06/2021&end=30/10/2021")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
