package com.blockbuster.gamesblockbuster.controllers;

import com.blockbuster.gamesblockbuster.models.GamePlatform;
import com.blockbuster.gamesblockbuster.repositories.GamePlatformRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/game_platform", consumes = MediaType.ALL_VALUE)
public class GamePlatformController {

    private final GamePlatformRepository gamePlatformRepository;

    public GamePlatformController(GamePlatformRepository gamePlatformRepository) {
        this.gamePlatformRepository = gamePlatformRepository;
    }

    @GetMapping
    List<GamePlatform> getAll() {
        return gamePlatformRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    ResponseEntity<GamePlatform> getSinglePlatform(@PathVariable("id") long id) {
        return gamePlatformRepository.findById(id)
                .map(platform -> ResponseEntity.ok().body(platform))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    GamePlatform createNewPlatform(@RequestBody GamePlatform newPlatform) {
        return gamePlatformRepository.save(newPlatform);
    }

    @PutMapping(path = {"/{id}"})
    ResponseEntity<GamePlatform> updatePlatform(@PathVariable("id") long id,
                                            @RequestBody GamePlatform gamePlatform) {
        return gamePlatformRepository.findById(id)
                .map(platform1 -> {
                    platform1.setGame(gamePlatform.getGame());
                    platform1.setPlatform(gamePlatform.getPlatform());
                    platform1.setQuantity(gamePlatform.getQuantity());
                    platform1.setBorrowedQuantity(gamePlatform.getBorrowedQuantity());
                    GamePlatform updatedPlatform = gamePlatformRepository.save(platform1);
                    return ResponseEntity.ok().body(updatedPlatform);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    ResponseEntity<Object> removePlatform(@PathVariable("id") long id) {
        return gamePlatformRepository.findById(id)
                .map(platform -> {
                    gamePlatformRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    void removeAllPlatform() {
        gamePlatformRepository.deleteAll();
    }


}
