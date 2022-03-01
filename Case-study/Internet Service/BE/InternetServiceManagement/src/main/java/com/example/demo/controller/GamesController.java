package com.example.demo.controller;

import com.example.demo.entity.Game;
import com.example.demo.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/games")
public class GamesController {
    @Autowired
    GameServiceImpl gameService;

    @GetMapping(value = {"/", "/list"})
    public ResponseEntity<Page<Game>> findAllGame(@PageableDefault(size = 6) Pageable pageable) {
        Page<Game> listGame = this.gameService.findAll(pageable);
        if (listGame.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listGame, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable String id) {
        Game gameId = this.gameService.findById(id);
        if (gameId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public Page<Game> searchByKey(@RequestParam String key, @PageableDefault(value = 6) Pageable pageable){
        Page<Game> gameList = gameService.searchByName(key, pageable);
        if (gameList.isEmpty()){
            return this.gameService.findAll(pageable);
        }
        return gameList;
    }

    @GetMapping(value = "/searchCategory")
    public Page<Game> searchByCategory(@RequestParam String key, @PageableDefault(value = 6) Pageable pageable){
        Page<Game> gameList = gameService.searchByCategory(key, pageable);
        if (gameList.isEmpty()){
            return this.gameService.findAll(pageable);
        }
        return gameList;
    }

    @GetMapping(value = "/category")
    public Set<String> getCategoryGame(){
        return this.gameService.getCategoryGame();
    }
}
