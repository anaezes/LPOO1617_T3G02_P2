package GameLogic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import GameLogic.gameobjects.*;
import GameLogic.gameobjects.Bird;
import GameLogic.gameobjects.Branch;
import GameLogic.gameobjects.Star;
import GameLogic.gameobjects.Wall;
import GameLogic.gameobjects.Water;
import GameView.FlyChicken;

/**
 * Class GameMain
 * <br> Implements the logic of the game
 */
public class GameMain {

    private static final int WALL_X_OFFSET = -40;
    private static final int BRANCH_SPACING = 50;
    private static final int BRANCH_COUNT = 10;

    private GameLogic.EnumGameLevel level;
    private EnumGameState state;

    private int eatenApples;
    private GameLogic.gameobjects.Bird bird;

    private Array<GameLogic.gameobjects.Branch> branches;

    private GameLogic.gameobjects.Water water;
    private GameLogic.gameobjects.Apple apple;
    private GameLogic.gameobjects.Star star;
    private static GameMain instance = null;
    private  Random rand;

    private GameLogic.gameobjects.Wall leftWall, rightWall;
    private Vector2 leftWallPos1, leftWallPos2, rightWallPos1, rightWallPos2;

    private int lives;
    private int currDist;
    private long timeCount;
    private long currTime;
    private int score;
    private long timeSinceCollision;


    /**
     * Create a new game with
     * <br> zero score
     * <br> zero distance
     * <br> zero time passed
     * <br> three lives
     * @param l current level
     */
    public GameMain(EnumGameLevel l) {
        level = l;
        state = EnumGameState.Running;
        eatenApples = 0;
        rand = new Random();

        currDist = 0;
        lives = 3;
        timeCount = 0;
        currTime = System.currentTimeMillis();
        score = 0;
        timeSinceCollision = System.nanoTime();
    }

    /**
     * Increments time passed
     * @param dt time
     */
    public void updateTime(float dt) {
        timeCount+=dt;
    }

    /**
     * Update lives to this value
     * @param value new lives
     */
    public void updateLives(int value) {
            lives+=value;
    }

    /**
     * Update scores to this value
     * @param sc  new score
     */
    public void updateScore(int sc) {
        score+=sc;
    }

    /**
     * Return actual Score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Change Score to this value
     * @param score1  new score
     */
    public void setScore( int score1){
        score=score1;
    }

    /**
     * Return actual number of lives
     * @return lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Change lives to this value
     * @param live new lives
     */
    public void setLives(int live) {
        lives = live;
    }

    /**
     *
     * @return
     */
    public long getCurrTime() {
        timeCount = (System.currentTimeMillis() - currTime)/1000;
        System.out.println("TIME:"+ timeCount);
        return timeCount;
    }


    public void createBird(int width) {
        bird = Factory.createBird(level, 100, width);
        currDist = (int)bird.getPosition().y;
    }

    public void createWater() {
        water = Factory.createWater(level, 0, -478);
    }

    public void createApple(int x, int y){
        this.apple = new GameLogic.gameobjects.Apple(x, y);
    }

    public void createStar(int x, int y){
        this.star = new GameLogic.gameobjects.Star(x, y);
    }

    public void createBranchs() {
        branches = new Array<GameLogic.gameobjects.Branch>();
        for (int i = 1; i < BRANCH_COUNT; i++)
            branches.add(Factory.createBranch(level, 0, i * (BRANCH_SPACING + GameLogic.gameobjects.Branch.B_HEIGHT) + (int)bird.getPosition().y));
    }

    public void createWalls(OrthographicCamera cam) {

        leftWall = Factory.createLeftWall(level);
        rightWall = Factory.createRightWall(level);

        leftWallPos1 = new Vector2(WALL_X_OFFSET, cam.position.y - cam.viewportHeight/2);
        leftWallPos2 = new Vector2(WALL_X_OFFSET, (cam.position.x - cam.viewportWidth/2) + leftWall.getTexture().getHeight());

        rightWallPos1 = new Vector2(FlyChicken.WIDTH/2 - (rightWall.getTexture().getWidth() + WALL_X_OFFSET), cam.position.y - cam.viewportHeight/2);
        rightWallPos2 = new Vector2(FlyChicken.WIDTH/2- (rightWall.getTexture().getWidth() + WALL_X_OFFSET), (cam.position.x - cam.viewportWidth/2) + rightWall.getTexture().getHeight());
    }

    public EnumGameLevel getCurrentGameLevel() {
        return level;
    }

    public void setGameLevel(EnumGameLevel level) {
        this.level = level;
    }

    public Bird getGameBird() {
        return bird;
    }

    public GameLogic.gameobjects.Apple getApple() {
        return apple;
    }

    public Star getStar() {
        return star;
    }

    public void disposeApple() {
        this.apple = null;
    }

    public void disposeStar() {
        this.star = null;
    }

    public Water getWater() {
        return water;
    }

    public GameLogic.gameobjects.Wall getLeftWall() {
        return leftWall;
    }
    public Wall getRightWall() {
        return rightWall;
    }

    public Array<GameLogic.gameobjects.Branch> getGameBranches() {
        return branches;
    }

    public void updateBirdPos(float delta) {
        bird.update(delta);
        updateDist();
    }

    public void updateState() {
        if(lives <= 0)
            state = EnumGameState.Lose;
    }

    public EnumGameState getState() {
        return state;
    }

    public boolean checkCollisionsBranchs() {
        long delta_time =(System.nanoTime() - timeSinceCollision)/ 1000000;
        TimeUnit.SECONDS.convert(delta_time, TimeUnit.NANOSECONDS);
        if(delta_time <= 200) {
            return true;
        }

        for (int i=1; i < branches.size; i++){
            if(Intersector.overlaps(bird.getBounds(), branches.get(i).getBoundsLeftBranch()) ||
                    Intersector.overlaps(bird.getBounds(), branches.get(i).getBoundsRightBranch())) {
                    updateLives(-1);
                    updateState();
                    timeSinceCollision = System.nanoTime();
                return true;
                }
            }

            return false;
    }

    public boolean checkCollisionsWater() {
        if(Intersector.overlaps(bird.getBounds(), water.getWaterBounds())) {
            state = EnumGameState.Lose;
            return true;
        }
        return false;
    }

    public void checkScore(final int score) {
        if (score > FlyChicken.getInstance().scores.get(4).getPlayerPoints()) {
            Gdx.input.getTextInput(new Input.TextInputListener() {
                @Override
                public void input(String text) {
                    Score playerScore = new Score(text, score);
                    FlyChicken.getInstance().AddScore(playerScore);
                }

                @Override
                public void canceled() {
                    Score playerScore = new Score("Anonymous", score);
                    FlyChicken.getInstance().AddScore(playerScore);
                }
            }, "New High Score", "", "Your Name");

        }
    }

    public int getEatenApples() {
        return eatenApples;
    }

    public void setEatenApples(int eatenApples) {
        this.eatenApples = eatenApples;
    }

    public boolean checkAppleCollision(){
       if( Intersector.overlaps(apple.getAppleBounds(), bird.getBounds())) {
           this.eatenApples += 1;
           updateScore(50);
           return true;
       }
        return false;
    }

    public boolean checkStarCollision(){
        if( Intersector.overlaps(star.getStarBounds(), bird.getBounds())) {
            this.lives += 1;
            return true;
        }
        return false;
    }

    public void updateAwards(OrthographicCamera cam) {
        if (cam.position.y - (cam.viewportHeight / 2) > apple.getPosY() + apple.getAppleTexture().getHeight()) {
            apple.setPosX(getXRandomAxis(cam));
            apple.setPosY(getCurrentYAxis(cam));
            apple.getAppleBounds().setPosition(apple.getPosX(), apple.getPosY());
        }

        if(cam.position.y - (cam.viewportHeight / 2) > star.getPosY() + star.getStarTexture().getHeight()) {
            star.setPosX(getXRandomAxis(cam));
            star.setPosY(getCurrentYAxis(cam));
            star.getStarBounds().setPosition(star.getPosX(), star.getPosY());
        }
    }

    public void updateBranches(OrthographicCamera cam) {
        for (GameLogic.gameobjects.Branch branch : branches) {
            if (cam.position.y - (cam.viewportHeight / 2)  > branch.getPosRightBranch().y + branch.getRightBranch().getHeight())
                branch.reposition(branch.getPosRightBranch().y + ((Branch.B_HEIGHT + BRANCH_SPACING) * BRANCH_COUNT));
        }
    }



    public Vector2 getLeftWallPos1() {
        return leftWallPos1;
    }

    public Vector2 getLeftWallPos2() {
        return leftWallPos2;
    }

    public Vector2 getRightWallPos1() {
        return rightWallPos1;
    }

    public Vector2 getRightWallPos2() {
        return rightWallPos2;
    }

    public int getXRandomAxis(OrthographicCamera cam) {
        int min = leftWall.getTexture().getWidth();
        int max = (int)cam.viewportWidth-rightWall.getTexture().getWidth();
        int x = rand.nextInt((max- min)+1)+min;
        return x;
    }

    public int getCurrentYAxis(OrthographicCamera cam) {
        int min = 0 + (int)cam.position.y;
        int max = FlyChicken.HEIGHT + (int)cam.position.y;
        int y = bird.getPosY() + rand.nextInt((max - min)+1)+min;
        return y;
    }

    public void updateDist() {

        if((int)bird.getPosition().y - currDist >= 500) {
            score += 1;
            currDist = (int)bird.getPosition().y;
        }


    }
}
