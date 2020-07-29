package com.blockbuster.gamesblockbuster.controllers;

public abstract class GameState {
    public GameState rent() throws Exception {
        throw new Exception("No more copies available.");
    }

    public GameState returnGame() throws Exception {
        throw new Exception("This copy is already available");
    }
}

class Rented extends GameState {
    public GameState returnGame() { return new Available(); }
}

class Available extends GameState {
    public GameState rent() { return new Rented(); }
}


