package com.sm3.connect4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.sm3.connect4.model.Game;

/**
 * Unit test for app
 */
public class GameTest {
    @Test
    public void testPlayerColor() {
        Game g1 = new Game("Hard", "Red");
        Assertions.assertTrue(g1.getPlayerColor() == 1);
        Game g2 = new Game("Hard", "Yellow");
        Assertions.assertTrue(g2.getPlayerColor() == 2);
    }
}
