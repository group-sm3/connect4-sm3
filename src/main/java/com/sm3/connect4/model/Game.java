package com.sm3.connect4.model;

import java.util.Optional;

public class Game extends Model {

    private boolean redTurn;
    private boolean isEasy;
    private boolean playerRed;
    
	private final int COLUMNS = 7;
	private final int ROWS = 6;
    private boolean redMove = true;
    private Character red = 'r';
    private Character yellow = 'y';
	private Character[][] grid = new Character[COLUMNS][ROWS];

    //Constructor for Singleplayer Game
    public Game(String difficulty, String color) {
        if (difficulty == "Easy")
            isEasy = true;
        else
            isEasy = false;

        if (color == "Red")
            playerRed = true;
        else
            playerRed = false;

    }


    //Optional not working with char
    private Optional<Character> getDisc(int column, int row) {
		if (column < 0 || column >= COLUMNS || row < 0 || row >= ROWS) 
			return Optional.empty();
		return Optional.ofNullable(grid[column][row]);
    }
    
    private void addDisc(Character disc, int column) {
        
		//Checks next available slot in column
        int row = ROWS - 1;
        do {
            if (this.grid[column][row] != null)
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

        ModelEvent me = new ModelEvent(this, 1, "", column, row, disc);
        notifyChanged(me);
    }

    public void playerColumnChoice(int column) {

        if (playerRed)
            addDisc(red, column);
        else
            addDisc(yellow, column);
    }
    
}