package com.blockbuster.gamesblockbuster.controllers;

import com.blockbuster.gamesblockbuster.models.User;
import com.blockbuster.gamesblockbuster.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/user"})
public class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    ResponseEntity<User> getSingleUser(@PathVariable("id") long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    User createNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping(path = {"/{id}"})
    ResponseEntity<User> updateUser(@PathVariable("id") long id,
                                    @RequestBody User user) {
        return userRepository.findById(id)
                .map(user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    user1.setMail(user.getMail());
                    user1.setType(user.getType());
                    User updatedUser = userRepository.save(user1);
                    return ResponseEntity.ok().body(updatedUser);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    ResponseEntity<User> removeUser(@PathVariable("id") long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().body(user);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    void removeAllUser() {
        userRepository.deleteAll();
    }
}
