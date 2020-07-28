package com.blockbuster.gamesblockbuster.controllers;

import com.blockbuster.gamesblockbuster.models.Platform;
import com.blockbuster.gamesblockbuster.repositories.PlatformRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/platform", consumes = MediaType.ALL_VALUE)
public class PlatformController {

    private final PlatformRepository platformRepository;

    PlatformController(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @GetMapping
    List<Platform> getAllPlatform() {
        return platformRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    ResponseEntity<Platform> getSinglePlatform(@PathVariable("id") long id) {
        return platformRepository.findById(id)
                .map(platform -> ResponseEntity.ok().body(platform))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    Platform createNewPlatform(@RequestBody Platform newPlatform) {
        return platformRepository.save(newPlatform);
    }

    @PutMapping(path = {"/{id}"})
    ResponseEntity<Platform> updatePlatform(@PathVariable("id") long id,
                                            @RequestBody Platform platform) {
        return platformRepository.findById(id)
                .map(platform1 -> {
                    platform1.setPlatform(platform.getPlatform());
//                    platform1.setGames(platform.getGames());
                    Platform updatedPlatform = platformRepository.save(platform1);
                    return ResponseEntity.ok().body(updatedPlatform);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    ResponseEntity<Object> removePlatform(@PathVariable("id") long id) {
        return platformRepository.findById(id)
                .map(platform -> {
                    platformRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    void removeAllPlatform() {
        platformRepository.deleteAll();
    }
}
