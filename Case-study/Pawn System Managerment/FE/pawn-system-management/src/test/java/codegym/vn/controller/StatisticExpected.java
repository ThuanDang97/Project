package codegym.vn.controller;

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
class StatisticExpected {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void statisticExpected_7() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/statisticExpected?&")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    void statisticExpected_8() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/statisticExpected?start=&end=")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void statisticExpected_10() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/statisticExpected?start=01/01/2021&end=30/02/2021")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void statisticExpected_11() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/statisticExpected?start=01/06/2021&end=30/11/2021")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
