package com.blockbuster.gamesblockbuster.controllers;

import com.blockbuster.gamesblockbuster.models.Game;
import com.blockbuster.gamesblockbuster.repositories.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/game"})
public class GameController {

    private final GameRepository gameRepository;

    GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping(path = {"{/id}"})
    ResponseEntity<Game> getSingleGame(@PathVariable("id") long id) {
        return gameRepository.findById(id)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    Game createNewGame(@RequestBody Game newGame) {
        return gameRepository.save(newGame);
    }

    @PutMapping(path = {"{/id}"})
    ResponseEntity<Game> updateGame(@PathVariable("id") long id,
                                    @RequestBody Game game) {
        return gameRepository.findById(id)
                .map(game1 -> {
                    game1.setTitle(game.getTitle());
                    game1.setReleaseYear(game.getReleaseYear());
                    game1.setTotalQuantity(game.getTotalQuantity());
                    game1.setBorrowedQuantity(game.getBorrowedQuantity());
                    game1.setGenre(game.getGenre());
                    game1.setPlatforms(game.getPlatforms());
                    Game updatedGame = gameRepository.save(game1);
                    return ResponseEntity.ok().body(updatedGame);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"{/id}"})
    ResponseEntity<Game> removeGame(@PathVariable("id") long id) {
        return gameRepository.findById(id)
                .map(game -> {
                    gameRepository.deleteById(id);
                    return ResponseEntity.ok().body(game);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    void removeAllGames() {
        gameRepository.deleteAll();
    }
}
