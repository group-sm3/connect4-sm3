package view;

import controller.Main;
import model.ModelListener;

public class ViewHandler {

	public static void selectAction(String selection) {
		Main.getController().menuSelection(selection);
	}

	public static void spStart(String difficulty, String color, ModelListener ml) {
		Main.getController().spStart(difficulty, color, ml);
	}
	
	public static void selectColumn(int column) {
		Main.getController().getGameController().playerColumnClick(column);
	}
}
