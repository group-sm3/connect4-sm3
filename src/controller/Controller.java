package controller;
import model.Model;
import view.MenuView;

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
				break;
			case MenuView.MPGAME: 
				//TODO
				break;
			case MenuView.SETTINGS: 
				//TODO
				break;
		}
		
	}
	

}
