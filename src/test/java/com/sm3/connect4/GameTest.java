package com.sm3.connect4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.sm3.connect4.model.Game;
import com.sm3.connect4.model.AI;

/**
 * JUnit test for the Game class
 */
public class GameTest {
    private final int COLUMNS = 7;
    private final int ROWS = 6;
	private final int red = 1;
    private final int yellow = 2;
    private int difficulty = 0;
    private int[][] grid = new int[COLUMNS][ROWS];
    private int counter[] = { 0, 0, 0, 0, 0, 0, 0 };
    
    @Test
    public void testPlayerColor() {
        Game g1 = new Game("Hard", "Red");
        Assertions.assertTrue(g1.getPlayerColor() == 1);
        Game g2 = new Game("Hard", "Yellow");
        Assertions.assertTrue(g2.getPlayerColor() == 2);
    }

    @Test
    public void testGameDifficulty() {
        Game g1 = new Game("Easy", "Red");
        Assertions.assertTrue(g1.getDifficulty() == 1);
        Game g2 = new Game("Hard", "Yellow");
        Assertions.assertTrue(g2.getDifficulty() == 3);
    }

    @Test
    public void testAIcolumnSelection() { 
        this.difficulty = 3;
        AI bot = new AI(red, ROWS, COLUMNS, grid, counter, difficulty);

        boolean pass = true;
        int choice = bot.BotTurn();
        if (choice < 0 || choice >= COLUMNS)
            pass = false;
        Assertions.assertTrue(pass);
    }
}
