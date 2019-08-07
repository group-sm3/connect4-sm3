package com.sm3.connect4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.sm3.connect4.model.Game;

/**
 * JUnit test for the Game class
 */
public class GameTest {
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

}
