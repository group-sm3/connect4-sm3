package controller;

import model.Game;
import model.Model;
import model.ModelListener;
import view.MenuView;
import view.GameView;
import view.Window;

public class Controller {
	
	private Model model;
	private GameController gamecontroller;
	private Window window;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void menuSelection(String selection) {
		
		switch (selection) {
			case MenuView.SPGAME:
				//TODO
				window = new GameView();
				window.displayWindow("SP");
				break;
			case MenuView.MPGAME: 
				//TODO
				window = new GameView();
				window.displayWindow("MP");
				break;
			case MenuView.LEADERBOARD: 
				//TODO
				break;
		}
		
	}

	public void spStart(String difficulty, String color, ModelListener ml) {
		
		this.model = new Game(difficulty, color);
		getModel().addModelListener(ml);
		this.gamecontroller = new GameController((Game)getModel());
	}

	public Model getModel() { return this.model; }

	public GameController getGameController() { return this.gamecontroller; }
}
