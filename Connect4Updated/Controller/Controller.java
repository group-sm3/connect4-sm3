package com.sm3.connect4.controller;

import com.sm3.connect4.model.Game;
import com.sm3.connect4.model.Model;
import com.sm3.connect4.model.ModelListener;
import com.sm3.connect4.view.GameView;
import com.sm3.connect4.view.Window;

public class Controller {
 
 private Model model;
 private GameController gamecontroller;
 private Window window;
 
 public Controller(Model model) {
  this.model = model;
 }
 
 public void menuSelection(String selection) {

  if ( selection.equals("Singleplayer") ) {
   window = new GameView();
    window.displayWindow("SP");
  }
  else if ( selection.equals("Multiplayer") ) {
   window = new GameView();
   window.displayWindow("MP");
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
