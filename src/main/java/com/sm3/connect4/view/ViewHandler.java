package com.sm3.connect4.view;

import com.sm3.connect4.Gui;
import com.sm3.connect4.model.ModelListener;

/**
 * A class used by the view package to send requests to the controller to
 * interact with the model.
 */
public class ViewHandler {

	/**
	 * Notifies the controller on the selected button of the main menu.
	 * 
	 * @param selection A string to represent the text of the selected button.
	 */
	public static void selectAction(String selection) {
		Gui.getController().menuSelection(selection);
	}

	/**
	 * Notifies the controller of the user's selected difficulty and player color
	 * when starting a new game.
	 * 
	 * @param difficulty A string to represent the A.I.'s difficulty.
	 * @param color      A string to represent the player's color preference.
	 * @param ml         A ModelListener class object to reperesnet the view's
	 *                   listener of the model events.
	 */
	public static void gameStart(String difficulty, String color, ModelListener ml) {
		Gui.getController().spStart(difficulty, color, ml);
	}

	/**
	 * Notifies the controller of the user's selected column in the Connect 4 game.
	 * 
	 * @param column An integer used to represent the user's selected column.
	 */
	public static void selectColumn(int column) {
		Gui.getController().getGameController().playerColumnClick(column);
	}

	/**
	 * Notifies the controller that it is the A.I.'s turn to play in the game.
	 */
	public static void botTurn() {
		Gui.getController().getGameController().botMove();
	}
}
