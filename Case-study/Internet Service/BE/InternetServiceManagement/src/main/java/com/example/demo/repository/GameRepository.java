package com.example.demo.repository;

import com.example.demo.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


public interface GameRepository extends JpaRepository<Game, String> {
    @Query("select g from Game g where g.gameName like %:key%")
    Page<Game> searchByName(String key, Pageable pageable);

    @Query("select g from Game g where g.gameCategory like %:key%")
    Page<Game> searchByCategory(String key, Pageable pageable);

    @Query("select g.gameCategory from Game g")
    Set<String> findGameByGameCategory();
}
