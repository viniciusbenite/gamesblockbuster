package com.blockbuster.gamesblockbuster.controllers;

import com.blockbuster.gamesblockbuster.models.ConcreteGame;
import com.blockbuster.gamesblockbuster.repositories.ConcreteGameRepository;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/concrete", consumes = MediaType.ALL_VALUE)
public class ConcreteGameController {

    private final ConcreteGameRepository concreteGameRepository;

    public ConcreteGameController(ConcreteGameRepository concreteGameRepository) {
        this.concreteGameRepository = concreteGameRepository;
    }

    @GetMapping
    List<ConcreteGame> getAllConcreteGames() {
        return concreteGameRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    ResponseEntity<ConcreteGame> getSingleConcreteGame(@PathVariable("id") long id) {
        return concreteGameRepository.findById(id)
                .map(concreteGame -> ResponseEntity.ok().body(concreteGame))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ConcreteGame createNewConcrete(@RequestBody ConcreteGame newConcreteGame) {
        return concreteGameRepository.save(newConcreteGame);
    }
}
