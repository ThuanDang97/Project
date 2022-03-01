package com.example.demo.controller;

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
public class StatisticalController_viewByComputer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testViewByComputer_1() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/view-by-computer")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testViewByComputer_2() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/view-by-computer?startTime=&endTime=")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testViewByComputer_3() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/view-by-computer?endTime=")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testViewByComputer_4() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/view-by-computer?startTime=")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testViewByComputer_5() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/view-by-computer?startTime=2020-03-03&endTime=2020-03-04")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testViewByComputer_6() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/statistical/view-by-computer?startTime=2020-01-01&endTime=2020-01-01")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
