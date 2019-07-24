package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/*
 * Game logic needs to be implemented in model package.
*/
public class Board {

	private final int TILE_SIZE = 60;
	private final int COLUMNS = 7;
	private final int ROWS = 6;
	private boolean redMove = true;
	private Disc[][] grid = new Disc[COLUMNS][ROWS];
	private Pane discPane = new Pane();
	
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
    		rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4); //change from / 4 to / 2
    		rect.setFill(Color.TRANSPARENT);
    		
			rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
			rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
			
			final int column = x;
			rect.setOnMouseClicked(e -> placeDisc(new Disc(redMove), column));

			list.add(rect);
    	}
    	
    	return list;
	}

	private Optional<Disc> getDisc(int column, int row) {
		if (column < 0 || column >= COLUMNS || row < 0 || row >= ROWS) {
			return Optional.empty();
		}
		return Optional.ofNullable(grid[column][row]);
	}

	private void placeDisc(Disc disc, int column) {
		
		//Checks next available slot in column
		int row = ROWS - 1;
        do {
            if (!getDisc(column, row).isPresent())
                break;
            row--;
        } while (row >= 0);

        if (row < 0)
            return;

		//adds disc to column
        grid[column][row] = disc;
        discPane.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);

        final int currentRow = row; //for gameover condition
		
		//Animation
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
			///check GameOver Condition
            redMove = !redMove;
        });
        animation.play();
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
	
	public Pane createBoardPane() {
		//create board pane
		Pane board = new Pane();
		board.getChildren().add(discPane);
    	Shape gridShape = makeGrid();
		board.getChildren().add(gridShape);
        board.getChildren().addAll(makeColumns());    
		
		//loads background image
		ImageView image = new ImageView(new Image(("res/game_background1.jpg")));

		//combines board with background
		StackPane spane = new StackPane();
		spane.setMargin(board, new Insets(60, 160, 40, 160));
		spane.getChildren().addAll(image, board);

		//creates textfield 
		TextField gameinfo = new TextField();
		gameinfo.setEditable(false);
		gameinfo.setPrefHeight(60);
		gameinfo.setText("Game start...");

		VBox vbox = new VBox(spane, gameinfo);
    	return vbox;
	}

}
