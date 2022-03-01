package com.example.demo.GameTest;

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
public class GameRestController_deleteGame {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testDeleteGame_25() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/games/delete/{id}", "null")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteGame_26() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/games/delete/{id}", "")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteGame_27() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/games/delete/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteGame_28() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/games/delete/{id}", "5")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
