package view;

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
 * Game logic needs to be implemented in model package.
*/
public class Board {

	private final int TILE_SIZE = 60;
	private final int COLUMNS = 7;
	private final int ROWS = 6;
	private boolean redMove = true;
	private boolean canGo;
	private Disc[][] grid = new Disc[COLUMNS][ROWS];
	private Pane discPane = new Pane();
	
	public Board() {
		
	}

	private Shape makeGrid() {
    	Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);
    	
    	for (int y = 0; y < ROWS; y++) 	{
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
    
    private List<Rectangle> makeColumns() {
    	List<Rectangle> list = new ArrayList<>();
    	
    	for (int x = 0; x < COLUMNS; x++) {
    		Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
    		rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
    		rect.setFill(Color.TRANSPARENT);
    		
			rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
			rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
			
			final int column = x;
			rect.setOnMouseClicked(e -> ViewHandler.selectColumn(column) ); //notifies controller

			list.add(rect);
    	}
    	
    	return list;
	}


	public void placeDisc(Character color, int column, int row) {
		//creates disc with correct color
		Disc disc;
		if (color.charValue() == 'r')
			disc = new Disc(true);
		else if (color.charValue() == 'y')
			disc = new Disc(false);
		else
			return;

		//adds disc to column
        grid[column][row] = disc;
        discPane.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);
		
		//Animation
		row = (ROWS-1) - row;
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
			///inform player that movement occured
        });
        animation.play();
    }	
	
	public Pane createBoardPane() {
		//create board pane
		Pane board = new Pane();
		board.setId("gameboard");
		board.getChildren().add(discPane);
    	Shape gridShape = makeGrid();
		board.getChildren().add(gridShape);
		board.getChildren().addAll(makeColumns());
		return board;
	}

	private class Disc extends Circle {
        private final boolean red;
        public Disc(boolean red) {
            super(TILE_SIZE / 2, red ? Color.RED : Color.YELLOW);
            this.red = red;
            setCenterX(TILE_SIZE / 2);
            setCenterY(TILE_SIZE / 2);
		}
	}
}
