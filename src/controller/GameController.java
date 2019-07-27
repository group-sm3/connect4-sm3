package controller;

import model.Game;

public class GameController {

    private Game game;

    public GameController(Game model) {
        this.game = model;
    }

    public Game getGame() { return this.game; }

    public void playerColumnClick(int column) {
        getGame().playerColumnChoice(column);
	}
}
