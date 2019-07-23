package controller;

import javafx.collections.FXCollections;
import model.Model;
import view.MenuView;
import view.GameView;
import view.Window;

public class Controller {
	
	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	//TODO methods that handle key press / actions to Model
	public void menuSelection(String selection) {
		
		switch (selection) {
			case MenuView.SPGAME:
				//TODO
				Window newWindow = new GameView();
				newWindow.displayWindow();
				break;
			case MenuView.MPGAME: 
				//TODO
				break;
			case MenuView.LEADERBOARD: 
				//TODO
				break;
		}
		
	}
	

}
