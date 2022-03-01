package com.example.demo.GameTest;

import com.example.demo.controller.GamesController;
import com.example.demo.entity.Game;
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
public class GameRestController_getAllGame {
    @Autowired
    private GamesController gamesController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testFindAllGame_6(){
        ResponseEntity<Page<Game>> responseEntity = this.gamesController.findAllGame(PageRequest.of(2,2));
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(4, responseEntity.getBody().getTotalElements());
    }

    @Test
    public void testGetListGame_5() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/games/list")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
