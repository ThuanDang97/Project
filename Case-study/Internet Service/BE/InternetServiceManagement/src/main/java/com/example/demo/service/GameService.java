package com.example.demo.service;

import com.example.demo.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface GameService {
    Page<Game> findAll(Pageable pageable);
    void deleteGame(String id);
    Game findById(String id);
    Page<Game> searchByName(String key, Pageable pageable);
    Page<Game> searchByCategory(String key, Pageable pageable);
    Set<String> getCategoryGame();
}
