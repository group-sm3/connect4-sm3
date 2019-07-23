package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.effect.Lighting; 
import javafx.stage.Stage;


public class Board {

	private final int TILE_SIZE = 80;
	private final int COLUMNS = 7;
	private final int ROWS = 6;
	
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
			list.add(rect);
    	}
    	
    	return list;
	}
	
	private Pane createBoardPane() {
    	Pane pane = new Pane();
        
    	Shape gridShape = makeGrid();
		pane.getChildren().add(gridShape);
        pane.getChildren().addAll(makeColumns());
        return pane;        
    }
    
    public Scene createBoardScene() {
    	return new Scene(createBoardPane());
    }
}
