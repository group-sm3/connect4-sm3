package model;

import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
	int column;
	int row;
	char color;
	public ModelEvent(Object obj, int id, String command, int column, int row, char color){
		super(obj, id, command);
		this.column = column;
		this.row = row;
		this.color = color;
	}

	public int getColumn() { return this.column; }

	public int getRow() { return this.row; }

	public char getColor() { return this.color; }
}
