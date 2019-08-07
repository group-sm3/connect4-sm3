package com.sm3.connect4.model;

import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
 int column;
 int row;
 char color;
 String message;
 boolean win;
 boolean content;
 
 public ModelEvent(Object obj, int id, String command, String message) {
  super(obj, id, command);
  this.message = message;
  this.content = false;
 }

 public ModelEvent(Object obj, int id, String command, int column, int row, char color, String message, boolean win) {
  super(obj, id, command);
  this.column = column;
  this.row = row;
  this.color = color;
  this.message = message;
  this.win = win;
  this.content = true;
 }

 public int getColumn() { return this.column; }

 public int getRow() { return this.row; }

 public char getColor() { return this.color; }

 public String getMessage() { return this.message; }

 public boolean getWin() { return this.win; }

 public boolean getContent() { return this.content; }

 public void setWin(boolean w) { this.win = w; }
}
