package com.blockbuster.gamesblockbuster.controllers;

import com.blockbuster.gamesblockbuster.models.GamePlatform;
import com.blockbuster.gamesblockbuster.models.Requests;
import com.blockbuster.gamesblockbuster.models.User;
import com.blockbuster.gamesblockbuster.repositories.GamePlatformRepository;
import com.blockbuster.gamesblockbuster.repositories.RequestRepository;
import com.blockbuster.gamesblockbuster.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/requests", consumes = MediaType.ALL_VALUE)
public class RequestController {

    private final RequestRepository requestRepository;
    private final GamePlatformRepository gamePlatformRepository;
    private final UserRepository userRepository;

    public RequestController(RequestRepository requestRepository, GamePlatformRepository gamePlatformRepository,
                             UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.gamePlatformRepository = gamePlatformRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    List<Requests> getAllRequests() {
        return requestRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    ResponseEntity<Requests> getSingleRequest(@PathVariable("id") long id) {
        return requestRepository.findById(id)
                .map(request -> ResponseEntity.ok().body(request))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    Requests createNewRequest(@RequestBody Requests newRequest) {
        //TODO this cant be on post! newRequest.getId() == 0, its auto generated
//        long _id = getUserId(newRequest.getId());
//        userRepository.findById(_id)
//                .map(user1 -> {
//                    user1.setUserRequests(newRequest);
//                    return null;
//                });
        return requestRepository.save(newRequest);
    }

    @PatchMapping(path = {"/{id}"})
    ResponseEntity<Requests> dealWithRequest(@PathVariable("id") long id,
                                             @RequestBody Requests request) {

        long _id = getGamePlatId(id);

        int totalQuantity = getTotalQuantity(_id);

        int borrowedQuantity = getBorrowedQuantity(_id);

        switch (request.getStatus()) {
            case "approved":
                if (borrowedQuantity >= totalQuantity) {
                    System.out.println("NO MORE GAMES TO RENT");
                    badRequest();
                } else {
                    gamePlatformRepository.updateBorrowedQuantity(_id);
                    System.out.println("Request approved");
                }
                break;
            case "denied":
                System.out.println("Request denied");
                break;
            case "returning":
                if (borrowedQuantity <= 0) {
                    System.out.println("NO MORE GAME TO RETURN");
                    badRequest();
                } else {
                    gamePlatformRepository.updatedReturnQuantity(_id);
                    System.out.println("Game returned");
                }
                break;
        }

        return requestRepository.findById(id)
                .map(request1 -> {
                    request1.setStatus(request.getStatus());
                    Requests updatedRequest = requestRepository.save(request1);
                    return ResponseEntity.ok().body(updatedRequest);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    ResponseEntity<Requests> removeRequest(@PathVariable("id") long id) {
        return requestRepository.findById(id)
                .map(requests -> {
                    requestRepository.deleteById(id);
                    return ResponseEntity.ok().body(requests);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    void removeAllRequests() {
        requestRepository.deleteAll();
    }


    // Auxiliary functions
    public ResponseEntity<String> badRequest() {
        return new ResponseEntity<String>(
                "No more games to rent",
                HttpStatus.BAD_REQUEST);
    }

    public long getGamePlatId(long id) {
        long _id = requestRepository.findById(id)
                .map(Requests::getRequestedGame)
                .map(GamePlatform::getId).orElse(-1L);
        System.out.println("GamePlatform ID is: " + _id);
        return _id;
    }

    public int getTotalQuantity(long id) {
        int totalQuantity = gamePlatformRepository.findById(id)
                .map(GamePlatform::getQuantity).orElse(-1);
        System.out.println("Total quantity is: " + totalQuantity);
        return totalQuantity;
    }

    public int getBorrowedQuantity(long id) {
        int borrowedQuantity = gamePlatformRepository.findById(id)
                .map(GamePlatform::getBorrowedQuantity).orElse(-1);
        System.out.println("Borrowed quantity is: " + borrowedQuantity);
        return borrowedQuantity;
    }

    public long getUserId(long id) {
        long userId = requestRepository.findById(id)
                .map(Requests::getRequestOwner)
                .map(User::getId).orElse(-1L);
        System.out.println(userId);
        return userId;
    }
}


