package Tests;

import org.junit.Assert;
import org.junit.Test;

import GameLogic.EnumGameLevel;
import GameLogic.GameMain;

public class TestLogic {
     @Test
    public void testBirdEatingApple() {
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createBird(200, 100, 100);
         game.getEatenApples();
         Assert.assertEquals(0, game.getEatenApples());
         game.createApple(100, 120, 100, 100);
         game.getGameBird().jump();
         Assert.assertNotEquals(0, game.getEatenApples());
         Assert.assertEquals(1, game.getEatenApples());
     }
}
