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
import GameLogic.gameobjects.Branch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestLogic {
     @Test
    public void testBirdEatingApple() {
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createBird(200, 100, 100);
         game.getEatenApples();
         assertEquals(0, game.getEatenApples());
         game.createApple(100, 120, 100, 100);
         game.setEatenApples(20);
         assertEquals(20,game.getEatenApples());
     }

     @Test
     public void testBirdWeight(){
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createBird(200, 100, 100);
         assertEquals(1, game.getGameBird().getWeight(), 0.1);
         game = new GameMain(EnumGameLevel.LevelTwo);
         game.createBird(200, 100, 100);
         Assert.assertNotEquals(1, game.getGameBird().getWeight(), 0.1);
         assertEquals(1, game.getGameBird().getWeight(), 5f);
         game = new GameMain(EnumGameLevel.LevelThree);
         game.createBird(200, 100, 100);
         Assert.assertNotEquals(1, game.getGameBird().getWeight(), 0.1);
         assertEquals(1, game.getGameBird().getWeight(), 10f);
     }

     @Test
    public void testWaterIncrement(){
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createWater(10, 10);
         assertEquals(game.getWater().getWaterIncrement(), 2);
         Assert.assertNotEquals(game.getWater().getWaterIncrement(), 3);
         game = new GameMain(EnumGameLevel.LevelTwo);
         game.createWater(10, 10);
         assertEquals(game.getWater().getWaterIncrement(), 3);
         Assert.assertNotEquals(game.getWater().getWaterIncrement(), 2);
         game = new GameMain(EnumGameLevel.LevelThree);
         game.createWater(10, 10);
         assertEquals(game.getWater().getWaterIncrement(), 4);
         Assert.assertNotEquals(game.getWater().getWaterIncrement(), 2);
     }

    @Test
    public void checkAppleCollision() {
         GameMain game = new GameMain(EnumGameLevel.LevelOne);
         game.createApple(100, 105, 10, 10);
         game.createBird(100, 10, 10);
         game.getGameBird().jump();
         game.checkAppleCollision();
         assertEquals(50, game.getScore());
         assertEquals(1, game.getEatenApples());
        game.disposeApple();
        assertTrue(game.getApple() == null);
     }

    @Test
    public void checkStarCollision() {
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createStar(100, 105, 10, 10);
        game.createBird(100, 10, 10);
        game.getGameBird().jump();
        game.checkStarCollision();
        assertEquals(4, game.getLives());
        game.disposeStar();
        assertTrue(game.getStar() == null);
    }

    @Test
    public void checkWaterCollision(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(100, 10, 10);
        game.createWater(100,100);
        game.getWater().setWaterIncrement(1);
        game.getWater().setWaterBoundsPosition(100, 100);
        game.getGameBird().jump();
        assertEquals(true, game.checkCollisionsWater());
        assertEquals(EnumGameState.Lose, game.getState());
    }

    @Test
    public void updateLivesBranch(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(100, 10, 10);
        game.createBranchs(100,100);
        game.getGameBranches().get(1).getBoundsLeftBranch().set(100, 100, 200, 200);
        game.getGameBranches().get(1).getBoundsRightBranch().set(100, 100, 200, 200);
        game.setTimeSinceCollision(205);
        game.checkCollisionsBranchs();
        assertEquals(2, game.getLives());
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
        assertEquals(0, game.getLives());
        assertEquals(EnumGameState.Lose, game.getState());
        assertEquals(EnumGameLevel.LevelOne, game.getCurrentGameLevel());
        game.setGameLevel(EnumGameLevel.LevelThree);
        assertEquals(EnumGameLevel.LevelThree, game.getCurrentGameLevel());

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
        assertEquals(scores.get(0).getPlayerName(), "ui");
        assertEquals(scores.get(0).getPlayerPoints(), 2000);
        assertEquals(scores.get(1).getPlayerName(), "uia");
        assertEquals(scores.get(1).getPlayerPoints(), 300);
        assertEquals(scores.get(2).getPlayerName(), "ai");
        assertEquals(scores.get(2).getPlayerPoints(), 100);
        game.setScore(100);
        assertEquals(100, game.getScore());
    }

    @Test
    public void updateTime(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.updateTime(10);
        assertEquals(0, game.getCurrTime());
    }

    @Test
    public void setLives(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.setLives(10);
        assertEquals(10,game.getLives());
    }

    @Test
    public void createWalls(){
        OrthographicCamera cam = new OrthographicCamera();
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createWalls(cam, 10, 10);
        assertEquals(game.getLeftWall().getWidth(), 10);
        assertEquals(game.getRightWall().getWidth(), 10);
    }


    @Test
    public void testGets(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createStar(100, 105, 10, 10);
        game.createApple(100, 105, 10, 10);
        assertEquals(100, game.getStar().getPosX());
        assertEquals(100, game.getApple().getPosX());
        assertEquals(10, game.getStar().getWidth());
        assertEquals(10, game.getApple().getHeight());
    }

    @Test
    public void updateDistance(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(100, 10, 10);
        game.updateDist();
        assertEquals(0, game.getScore());
    }

    @Test
    public void updateAwards(){
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
    public void updateBranches(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(200, 10, 10);
        game.createBranchs(10, 10);
        game.updateBranches(40);
        Assert.assertNotEquals(10, game.getGameBranches().get(0).getPosX());
    }

    @Test
    public void testUpdateBird(){
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        game.createBird(200, 10, 10);
        assertEquals(100.0f,  game.getGameBird().getPosition().x, 0);
        game.getGameBird().jump();
        game.getGameBird().update(0.13635568f, -5f);
        Assert.assertNotEquals(100.000f, game.getGameBird().getPosition().x, 0);
    }

    @Test
    public void testSetLimitsBird() {
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        OrthographicCamera cam = new OrthographicCamera();
        game.createBird(200, 10, 10);
        game.createWater(200, 200);
        game.createWalls(cam, 20,  30);
        Assert.assertEquals(0,  game.getGameBird().birdPosMinX, 0);
        game.getGameBird().setValidPositionsX(game.getLeftWallPos1().x+game.getLeftWall().getWidth(), game.getRightWallPos1().x,
                game.getWater().getPosY()+game.getWater().getHeight());
        Assert.assertNotEquals(0,  game.getGameBird().birdPosMinX);
    }

    @Test
    public void testBranchReposition() {
        GameMain game = new GameMain(EnumGameLevel.LevelOne);
        OrthographicCamera cam = new OrthographicCamera();
        game.createBird(200, 10, 10);
        game.createBranchs(100,100);
        Branch br = game.getGameBranches().get(0);
        float pos = br.getPosLeftBranch().y;
        br.reposition(br.getPosRightBranch().y + ((br.B_HEIGHT + game.BRANCH_SPACING) * game.BRANCH_COUNT));
        Assert.assertNotEquals(pos,  br.getPosLeftBranch().x, 0);
    }
}
