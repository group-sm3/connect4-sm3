package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import model.AI;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.*;


/*
 * Game logic needs to be implemented in model package.
*/
public class Board {

	private final int TILE_SIZE = 60;
	private final int COLUMNS = 7;
	private final int ROWS = 6;
	private boolean redMove = true;
	private int[][] grid = new int[COLUMNS][ROWS];
	private Pane discPane = new Pane();
	private int counter[] = {5,5,5,5,5,5,5};//counter counts how many free spaces are left (includes 0)
	public Boolean gameOver = false;
	private AI bot;
	
	private Shape makeGrid() {
		this.bot = new AI();
		this.bot.SetColor(2);
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
			rect.setOnMouseClicked(e -> placeDisc(column));
			list.add(rect);
    	}
    	
    	return list;
	}
/*
	private Optional<Disc> getDisc(int column, int row) {
		if (column < 0 || column >= COLUMNS || row < 0 || row >= ROWS) {
			return Optional.empty();
		}
		return Optional.ofNullable(grid[column][row]);
	}*/

	private void placeDisc(int column) {
		if(counter[column] < 0)//if column is full
		{
			return;
		}
		Disc disc = new Disc(redMove);
		redMove = !redMove;
		if(redMove)
		{
	        grid[column][counter[column]] = 2; //yellow
		}
		else
		{
	        grid[column][counter[column]] = 1; //red
		}
        discPane.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);
		
		//Animation
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(counter[column] * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
			///check GameOver Condition
        });
        gameOver = CheckWinCondition(column, counter[column]); //if player won, gameOver will be true
        if(gameOver) {GameEnd(grid[column][counter[column]]);}
        counter[column]--; //column has one less space
        AIturn();
        animation.play();
    }
	
	private void AIturn()
	{
		int column = bot.BotTurn(grid, counter); //best column to place piece
		Disc disc = new Disc(redMove);
		redMove = !redMove;
		if(redMove)
		{
	        grid[column][counter[column]] = 2; //yellow
		}
		else
		{
	        grid[column][counter[column]] = 1; //red
		}
        discPane.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);
		
		//Animation
		TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(counter[column] * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
			///check GameOver Condition
        });
        gameOver = CheckWinCondition(column, counter[column]); //if player won, gameOver will be true
        if(gameOver) {GameEnd(grid[column][counter[column]]);}
        counter[column]--; //column has one less space
        animation.play();
	}

	
	private void GameEnd(int winner) //called on win condition true
	{
		//todo: make an actual end game screen and ui which will be displayed now
		if (winner == 1)
		{
		System.out.println("Red wins the game");	
		}
		else 
		{
			System.out.println("Yellow wins the game");	
		}
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
	
	public Boolean CheckWinCondition(int x, int y) //signals: false = no win, true = win
	{							
		//x and y indicate where the last piece was placed, check for local win condition
		int count = 0;
		for (int i = 0; i < 6; i++)		//check rows
		{
			if (grid[i][y] == grid[i + 1][y] && grid[i][y] != 0) 
			{
				count++;
			}
			else
			{
				count = 0;
			}
			if(count == 3)
			{
				System.out.println("win by row");
				return true;
			}
		}
		count = 0;
		for (int i = 0; i < 5; i++) 	//check column
		{
			if (grid[x][i] == grid[x][i+1] && grid[x][i] != 0) 
			{
				count++;
			}
			else {
				count = 0;
			}
			if(count == 3)
			{
				System.out.println("win by col");
				return true;
			}
		}
		count = 0;
		for (int i = -3; i < 4; i++) 		//check diagonal left
		{
			try
			{
				if(grid[x+i][y+i] == grid[x+i+1][y+i+1]) {
					if(grid[x+i][y+i] != 0)
					{
						count++;
					}
				}
				else 
				{	count = 0;	}
				
				if(count == 3)
				{
					System.out.println("win by l diag");
					return true;
				}
			    }
		    catch(ArrayIndexOutOfBoundsException exception) {
				//System.out.println("Error here");
			}
		}
		count = 0;
		for (int i = -3; i < 4; i++) 		//check diagonal right
		{
			try
			{
				if(grid[x+i][y-i] == grid[x+i+1][y-i-1]){
					if(grid[x+i][y-i] != 0)
					{
						//System.out.println("hit");
						count++;
					}
				}
				else 
				{	count = 0;	}
				
				if(count == 3)
				{
					System.out.println("win by r diag");
					return true;
				}
			    }
		    catch(ArrayIndexOutOfBoundsException exception) {
				//System.out.println("Error here");
			}
		}
		return false; //else, no win
	}
	
	public Pane createBoardPane() {
		//create board pane
		Pane board = new Pane();
		board.setId("gameboard");
		board.getChildren().add(discPane);
    	Shape gridShape = makeGrid();
		board.getChildren().add(gridShape);
        board.getChildren().addAll(makeColumns());    

		//combines board with background
		StackPane spane = new StackPane();
		spane.setMargin(board, new Insets(60, 160, 40, 160));
		spane.getChildren().addAll(board);

		//creates textfield 
		TextField gameinfo = new TextField();
		gameinfo.setId("gameinfo");
		gameinfo.setEditable(false);
		gameinfo.setPrefHeight(60);
		gameinfo.setText("Game start...");

		VBox vbox = new VBox(spane, gameinfo);
    	return vbox;
	}

	private void PrintGrid() //function to print the grid top to bottom, grid is inverted
	{
	for (int i = 0; i < 6; i++) {
		System.out.print(grid[0][i] + " " + grid[1][i] + " " + grid[2][i] + " " + grid[3][i] + " " + grid[4][i] + " " + grid[5][i] + " " + grid[6][i]);
		System.out.print("\n");
	}
	}
}
