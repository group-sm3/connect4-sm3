package com.sm3.connect4.view;

import com.sm3.connect4.Gui;
import com.sm3.connect4.model.ModelListener;

public class ViewHandler {

	public static void selectAction(String selection) {
		Gui.getController().menuSelection(selection);
	}

	public static void spStart(String difficulty, String color, ModelListener ml) {
		Gui.getController().spStart(difficulty, color, ml);
	}
	
	public static void selectColumn(int column) {
		Gui.getController().getGameController().playerColumnClick(column);
	}
}
