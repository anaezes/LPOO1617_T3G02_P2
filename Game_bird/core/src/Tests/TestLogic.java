package Tests;


import com.badlogic.gdx.graphics.OrthographicCamera;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import GameLogic.EnumGameLevel;
import GameLogic.EnumGameState;
import GameLogic.GameMain;
import GameLogic.Score;
import GameView.FlyChicken;

public class TestLogic {
     @Test
    public void testBirdEatingApple() {
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createBird(200, 100, 100);
         game.getEatenApples();
         Assert.assertEquals(0, game.getEatenApples());
         game.createApple(100, 120, 100, 100);
         game.setEatenApples(20);
         Assert.assertEquals(20,game.getEatenApples());
     }

     @Test
     public void testBirdWeight(){
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createBird(200, 100, 100);
         Assert.assertEquals(1, game.getGameBird().getWeight(), 0.1);
         game = new GameMain(EnumGameLevel.LevelTwo);
         game.createBird(200, 100, 100);
         Assert.assertNotEquals(1, game.getGameBird().getWeight(), 0.1);
         Assert.assertEquals(1, game.getGameBird().getWeight(), 5f);
         game = new GameMain(EnumGameLevel.LevelThree);
         game.createBird(200, 100, 100);
         Assert.assertNotEquals(1, game.getGameBird().getWeight(), 0.1);
         Assert.assertEquals(1, game.getGameBird().getWeight(), 10f);
     }

     @Test
    public void TestWaterIncrement(){
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createWater(10, 10);
         Assert.assertEquals(game.getWater().getWaterIncrement(), 2);
         Assert.assertNotEquals(game.getWater().getWaterIncrement(), 3);
         game = new GameMain(EnumGameLevel.LevelTwo);
         game.createWater(10, 10);
         Assert.assertEquals(game.getWater().getWaterIncrement(), 3);
         Assert.assertNotEquals(game.getWater().getWaterIncrement(), 2);
         game = new GameMain(EnumGameLevel.LevelThree);
         game.createWater(10, 10);
         Assert.assertEquals(game.getWater().getWaterIncrement(), 4);
         Assert.assertNotEquals(game.getWater().getWaterIncrement(), 2);
     }

    @Test
    public void CheckAppleCollision() {
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createApple(100, 105, 10, 10);
         game.createBird(100, 10, 10);
         game.getGameBird().jump();
         game.checkAppleCollision();
         Assert.assertEquals(50, game.getScore());
         Assert.assertEquals(1, game.getEatenApples());
     }

    @Test
    public void CheckStarCollision() {
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createStar(100, 105, 10, 10);
        game.createBird(100, 10, 10);
        game.getGameBird().jump();
        game.checkStarCollision();
        Assert.assertEquals(4, game.getLives());
    }

    @Test
    public void checkWaterCollision(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(100, 10, 10);
        game.createWater(100,100);
        game.getWater().setWaterIncrement(1);
        game.getWater().setWaterBoundsPosition(100, 100);
        game.getGameBird().jump();
        Assert.assertEquals(true, game.checkCollisionsWater());
        Assert.assertEquals(EnumGameState.Lose, game.getState());
    }

    @Test
    public void UpdateLivesBranch(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(100, 10, 10);
        game.createBranchs(100,100);
        game.getGameBranches().get(1).getBoundsLeftBranch().set(100, 100, 200, 200);
        game.getGameBranches().get(1).getBoundsRightBranch().set(100, 100, 200, 200);
        game.setTimeSinceCollision(205);
        game.checkCollisionsBranchs();
        Assert.assertEquals(2, game.getLives());
    }

    @Test
    public void checkIfLoseWhenHit3TimesBranch(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(100, 10, 10);
        game.createBranchs(100,100);
        for(int i=0; i<3; i++){
            game.getGameBranches().get(1).getBoundsLeftBranch().set(100, 100, 200, 200);
            game.getGameBranches().get(1).getBoundsRightBranch().set(100, 100, 200, 200);
            game.setTimeSinceCollision(205);
            game.checkCollisionsBranchs();
        }
        Assert.assertEquals(0, game.getLives());
        Assert.assertEquals(EnumGameState.Lose, game.getState());
        Assert.assertEquals(EnumGameLevel.LevelOne, game.getCurrentGameLevel());
        game.setGameLevel(EnumGameLevel.LevelThree);
        Assert.assertEquals(EnumGameLevel.LevelThree, game.getCurrentGameLevel());

    }

    @Test
    public void checkOrderOfScores(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        Score score1 = new Score("ai", 100);
        Score score2 = new Score("ui", 2000);
        Score score3 = new Score("uia", 300);
        ArrayList<Score> scores = new ArrayList<Score>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        Collections.sort(scores);
        Assert.assertEquals(scores.get(0).getPlayerName(), "ui");
        Assert.assertEquals(scores.get(0).getPlayerPoints(), 2000);
        Assert.assertEquals(scores.get(1).getPlayerName(), "uia");
        Assert.assertEquals(scores.get(1).getPlayerPoints(), 300);
        Assert.assertEquals(scores.get(2).getPlayerName(), "ai");
        Assert.assertEquals(scores.get(2).getPlayerPoints(), 100);
        game.setScore(100);
        Assert.assertEquals(100, game.getScore());
    }

    @Test
    public void UpdateTime(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.updateTime(10);
        Assert.assertEquals(0, game.getCurrTime());
    }

    @Test
    public void setLives(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.setLives(10);
        Assert.assertEquals(10,game.getLives());
    }

    @Test
    public void createWalls(){
        OrthographicCamera cam = new OrthographicCamera();
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createWalls(cam, 10, 10);
        Assert.assertEquals(game.getLeftWall().getWidth(), 10);
        Assert.assertEquals(game.getRightWall().getWidth(), 10);
    }


    @Test
    public void TestGets(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createStar(100, 105, 10, 10);
        game.createApple(100, 105, 10, 10);
        Assert.assertEquals(100, game.getStar().getPosX());
        Assert.assertEquals(100, game.getApple().getPosX());
        Assert.assertEquals(10, game.getStar().getWidth());
        Assert.assertEquals(10, game.getApple().getHeight());
    }

    @Test
    public void UpdateDistance(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(100, 10, 10);
        game.updateDist();
        Assert.assertEquals(0, game.getScore());
    }

    @Test
    public void UpdateAwards(){
        OrthographicCamera cam = new OrthographicCamera();
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(200, 10, 10);
        game.createWalls(cam, 20, 30);
        game.createStar(100, 105, 10, 10);
        game.createApple(100, 105, 10, 10);
        game.updateAwards(40, 50);
        Assert.assertNotEquals(100, game.getStar().getPosX());
        Assert.assertNotEquals(100, game.getApple().getPosX());
    }

    @Test
    public void UpdateBranches(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(200, 10, 10);
        game.createBranchs(10, 10);
        game.updateBranches(40);
        Assert.assertNotEquals(10, game.getGameBranches().get(0).getPosX());
    }


}
