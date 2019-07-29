package com.sm3.connect4.model;

import java.util.Optional;

public class Game extends Model {

	private final int COLUMNS = 7;
	private final int ROWS = 6;
    private final int red = 1;
    private final int yellow = 2;
    private boolean redTurn;
    private boolean isEasy;   
    private AI bot;
    private int playerColor;
    private boolean redMove = true;
    private int[][] grid = new int[COLUMNS][ROWS];
    private int counter[] = {5,5,5,5,5,5,5}; //counter counts how many free spaces are left (includes 0)

    //Constructor for Singleplayer Game
    public Game(String difficulty, String color) {
        if (difficulty == "Easy")
            isEasy = true;
        else
            isEasy = false;

        if (color == "Red") {
            playerColor = red;
            bot = new AI(yellow);
        }
        else {
            playerColor = yellow;
            bot = new AI(red);
        }
    }
    
    private void addDisc(int disc, int column) {
        
		//Checks next available slot in column
        int row = ROWS - 1;
        do {
            if (this.grid[column][row] != 0)
                break;
            row--;
            System.out.println(row);
        } while (row >= 0);
        row++;
        //if row is full
        if (row > ROWS-1)
            return;
        
		//adds disc to column 
        this.grid[column][row] = disc;

        //TODO check gameover conditions

        char color;
        if (disc == 1) {
            color = 'r';
        } else {
            color = 'y';
        }

        ModelEvent me = new ModelEvent(this, 1, "", column, row, color);
        notifyChanged(me);
    }

    public void playerColumnChoice(int column) {
        if (playerColor == red)
            addDisc(red, column);
        else
            addDisc(yellow, column);
    }

    
    public boolean CheckWinCondition(int x, int y) //signals: false = no win, true = win
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
		count = 0;
		for (int i = -3; i < 4; i++) 		//check diagonal right
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
		return false; //else, no win
	}
    
}