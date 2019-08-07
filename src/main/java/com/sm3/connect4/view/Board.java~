package com.sm3.connect4.view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.effect.Lighting;

/*
 * A class that creates the board of the Connect 4 game and contains game information for the view.
*/
public class Board {

	private final int TILE_SIZE = 60;
	private final int COLUMNS = 7;
	private final int ROWS = 6;
	private boolean playerMove = true;
	private Disc[][] grid = new Disc[COLUMNS][ROWS];
	private Pane discPane = new Pane();

	/**
	 * Creates a Connect 4 board with empty spaces for the discs.
	 * 
	 * @return A Shape class object to represent the board of the Connect 4 game.
	 */
	private Shape makeGrid() {
		Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);

		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				Circle circle = new Circle(TILE_SIZE / 2);
				circle.setCenterX(TILE_SIZE / 2);
				circle.setCenterY(TILE_SIZE / 2);
				circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
				circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);
				shape = shape.subtract(shape, circle);
			}
		}
		shape.setFill(Color.BLUE);
		shape.setEffect(new Lighting());
		return shape;
	}

	/**
	 * Creates an list of rectangles to represent each column of the Connect 4 game.
	 * 
	 * @return A list of Rectangle objects representing the columns of the game.
	 */
	private List<Rectangle> makeColumns() {
		List<Rectangle> list = new ArrayList<>();

		for (int x = 0; x < COLUMNS; x++) {
			Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
			rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
			rect.setFill(Color.TRANSPARENT);
			rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
			rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

			final int column = x;
			// notifes controller
			rect.setOnMouseClicked(e -> columnClick(column));

			list.add(rect);
		}

		return list;
	}

	/**
	 * Uses the ViewHandler to notify the controller on the clicked column by the
	 * user.
	 * 
	 * @param column An integer used to represent the selected column.
	 */
	private void columnClick(int column) {
		if (playerMove) {
			playerMove = false;
			ViewHandler.selectColumn(column);
		}
	}

	/**
	 * Sets the player move true or false.
	 * 
	 * @param pm A boolean to represent if it is the player's move.
	 */
	public void setPlayerMove(boolean pm) {
		this.playerMove = pm;
	}

	/**
	 * Places the disc's into the desired column and plays the animation.
	 * 
	 * @param color  A Character to represent the color of the placed disc.
	 * @param column A integer to represent the desired column to place the disc in.
	 * @param row    A integer to represent the desired row to place the disc in.
	 */
	public void placeDisc(Character color, int column, int row) {
		// creates disc with correct color
		Disc disc;
		if (color.charValue() == 'r')
			disc = new Disc(true);
		else if (color.charValue() == 'y')
			disc = new Disc(false);
		else
			return;

		// adds disc to column
		grid[column][row] = disc;
		discPane.getChildren().add(disc);
		disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);

		// Animation
		row = (ROWS - 1) - row;
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
		animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
		animation.setOnFinished(e -> {
			//// tell if move is over so game logic can continue
		});
		animation.play();
	}

	/**
	 * Creates the board of the game to be returned as a Pane.
	 * 
	 * @return A Pane class object to reprsent the game board.
	 */
	public Pane createBoardPane() {
		Pane board = new Pane();
		board.setId("gameboard");
		board.getChildren().add(discPane);
		Shape gridShape = makeGrid();
		board.getChildren().add(gridShape);
		board.getChildren().addAll(makeColumns());
		return board;
	}

	/**
	 * A private class that extends the Circle class object and is used to represent
	 * a game disc.
	 */
	private class Disc extends Circle {
		private final boolean red;

		/**
		 * Creates a Disc with the desired color and is fitted for the created board.
		 * 
		 * @param red A boolean to represent if the disc will be red(true) or
		 *            yellow(false).
		 */
		public Disc(boolean red) {
			super(TILE_SIZE / 2, red ? Color.RED : Color.YELLOW);
			this.red = red;
			setCenterX(TILE_SIZE / 2);
			setCenterY(TILE_SIZE / 2);
		}
	}
}
