package com.sm3.connect4.model;

import java.awt.event.ActionEvent;

/**
 * A class that is used to notify the view(ModelListener) when a disc has been
 * placed in the grid in the Game, or to send a message to the view.
 */
public class ModelEvent extends ActionEvent {
	int column;
	int row;
	char color;
	String message;
	boolean win;
	boolean content;

	/**
	 * Creates a ModelEvent if a disc was not placed and/or a message needs to be
	 * sent to the view.
	 * 
	 * @param obj     An Object for the superclass ActionEvent
	 * @param id      An integer for the superclass ActionEvent
	 * @param command A String for the superclass ActionEvent
	 * @param message A String for the message to the view.
	 */
	public ModelEvent(Object obj, int id, String command, String message) {
		super(obj, id, command);
		this.message = message;
		this.content = false;
	}

	/**
	 * Creates a ModelEvent with information about the placed disc in the Game
	 * model.
	 * 
	 * @param obj     An Object for the superclass ActionEvent
	 * @param id      An integer for the superclass ActionEvent
	 * @param command A String for the superclass ActionEvent
	 * @param column  A integer to represent the column of the disc
	 * @param row     A integer to represent the row of the disc
	 * @param color   A char to represent the color of the disc
	 * @param message A String for the message to the view.
	 * @param win     A boolean to represent if the game has been won.
	 */
	public ModelEvent(Object obj, int id, String command, int column, int row, char color, String message,
			boolean win) {
		super(obj, id, command);
		this.column = column;
		this.row = row;
		this.color = color;
		this.message = message;
		this.win = win;
		this.content = true;
	}

	/**
	 * Gets the column.
	 * 
	 * @return A integer to represent the column of the disc.
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * Gets the row.
	 * 
	 * @return A integer to represent the row of the disc.
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Gets the color.
	 * 
	 * @return A char to represent the color of the disc.
	 */
	public char getColor() {
		return this.color;
	}

	/**
	 * Gets the message.
	 * 
	 * @return A String to represent the message of the event.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Gets the win boolean.
	 * 
	 * @return A boolean to represent if the game is over.
	 */
	public boolean getWin() {
		return this.win;
	}

	/**
	 * Gets the content boolean.
	 * 
	 * @return A boolean to represent if the class object has initialized the
	 *         column, row, color, and win params.
	 */
	public boolean getContent() {
		return this.content;
	}

	/**
	 * Sets the win boolean.
	 * 
	 * @param w A boolean to represent if the game has been won and is over.
	 */
	public void setWin(boolean w) {
		this.win = w;
	}
}
