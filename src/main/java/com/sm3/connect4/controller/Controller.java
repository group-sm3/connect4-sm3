package com.sm3.connect4.controller;

import com.sm3.connect4.model.Game;
import com.sm3.connect4.model.Model;
import com.sm3.connect4.model.ModelListener;
import com.sm3.connect4.view.GameView;
import com.sm3.connect4.view.Window;

/**
 * This class represents the controller of the Connect 4 game and allows for
 * interaction from the view to the model.
 * 
 */
public class Controller {

 private Model model;
 private GameController gamecontroller;
 private Window window;

 /**
  * Creates the controller with a model.
  * 
  * @param model The controller's Model class object.
  */
 public Controller(Model model) {
  this.model = model;
 }

 /**
  * Used by the Main Menu to initiate a Singleplayer or Multiplayer game.
  * 
  * @param selection The string used to represent the type of game requested.
  */
 public void menuSelection(String selection) {

  if (selection.equals("Singleplayer")) {
   window = new GameView();
   window.displayWindow("SP");
  } else if (selection.equals("Multiplayer")) {
   window = new GameView();
   window.displayWindow("MP");
  }

 }

 /**
  * Used by the secondary menu of the singleplayer/local multiplayer start to initiate a game with
  * the player's set preferences.
  * 
  * @param difficulty The string used to represent the difficulty of the game.
  * @param color      The string used to represent the player's color in the
  *                   game.
  * @param ml         The ModelListener class object used to link the model with
  *                   the view.
  */
 public void spStart(String difficulty, String color, ModelListener ml) {

  this.model = new Game(difficulty, color);
  getModel().addModelListener(ml);
  this.gamecontroller = new GameController((Game) getModel());
 }

 /**
  * Gets the model.
  * 
  * @return A Model class object to represent the model.
  */
 public Model getModel() {
  return this.model;
 }

 /**
  * Gets the game controller.
  * 
  * @return A GameController class object to represent the game's controller.
  */
 public GameController getGameController() {
  return this.gamecontroller;
 }
}
