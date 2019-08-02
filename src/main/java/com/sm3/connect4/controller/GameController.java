package com.sm3.connect4.controller;

import com.sm3.connect4.model.Game;

public class GameController {

    private Game game;

    public GameController(Game model) {
        this.game = model;
    }

    public Game getGame() { return this.game; }

    public void playerColumnClick(int column) {
        getGame().playerColumnChoice(column);
    }
    
    public void botMove() {
        getGame().botColumnChoice();
    }
}
