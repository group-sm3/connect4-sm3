package com.sm3.connect4.controller;

import com.sm3.connect4.model.Game;

/**
 * This class represents the game controller of the Connect 4 application and
 * allows for interaction from the game view to the game.
 * 
 */
public class GameController {

    private Game game;

    /**
     * Creates the game controller with the model/game.
     * 
     * @param model The game controller's Game class object.
     */
    public GameController(Game model) {
        this.game = model;
    }

    /**
     * Gets the game.
     * 
     * @return A Game class object to represent the model/game.
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Notifies the game on which column was selected/clicked by the user.
     * 
     * @param column An integer used to represent the selected column.
     */
    public void playerColumnClick(int column) {
        getGame().playerColumnChoice(column);
    }

    /**
     * Notifies the game to allow for the AI to make a move.
     * 
     */
    public void botMove() {
        getGame().botColumnChoice();
    }
}
