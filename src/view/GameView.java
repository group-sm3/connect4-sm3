package view;

import model.Board;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GameView implements Window{
	
	private Stage stage;
	private Board board;
    
    public void displayWindow() {
		stage = new Stage();
		board = new Board();
    	stage.setScene(board.createBoardScene());
    	stage.show();
    }
}
