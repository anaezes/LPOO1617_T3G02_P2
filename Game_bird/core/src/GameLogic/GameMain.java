package GameLogic;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import GameLogic.gameobjects.Bird;
import GameLogic.gameobjects.Branch;
import GameLogic.gameobjects.Factory;
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


    public void createBird(int y, int textureWidth, int textureHeight) {
        bird = Factory.createBird(level, 100, y, textureWidth, textureHeight);
        currDist = (int)bird.getPosition().y;
    }

    public void createWater(int w, int h) {
        water = Factory.createWater(level, 0, -478, w, h);
    }

    public void createApple(int x, int y, int width, int height){
        this.apple = new GameLogic.gameobjects.Apple(x, y, width, height);
    }

    public void createStar(int x, int y, int w, int h){
        this.star = new GameLogic.gameobjects.Star(x, y, w, h);
    }

    public void createBranchs(int w, int h) {
        branches = new Array<GameLogic.gameobjects.Branch>();
        for (int i = 1; i < BRANCH_COUNT; i++)
            branches.add(new Branch(0, i * (BRANCH_SPACING + GameLogic.gameobjects.Branch.B_HEIGHT) + (int)bird.getPosition().y, w, h));
    }

    public void createWalls(OrthographicCamera cam, int width, int height) {
        leftWall = new Wall(WALL_X_OFFSET, (int)(cam.position.y - cam.viewportHeight/2), width, height);
        rightWall = new Wall(FlyChicken.WIDTH/2 - (width + WALL_X_OFFSET), (int)(cam.position.y - cam.viewportHeight/2),width, height);

        leftWallPos1 = new Vector2(WALL_X_OFFSET, cam.position.y - cam.viewportHeight/2);
        leftWallPos2 = new Vector2(WALL_X_OFFSET, (cam.position.x - cam.viewportWidth/2) + height);

        rightWallPos1 = new Vector2(FlyChicken.WIDTH/2 - (width + WALL_X_OFFSET), cam.position.y - cam.viewportHeight/2);
        rightWallPos2 = new Vector2(FlyChicken.WIDTH/2- (width + WALL_X_OFFSET), (cam.position.x - cam.viewportWidth/2) + height);
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

    public void updateBirdPos(float delta, float ax) {
        bird.update(delta, ax);
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

    public boolean checkScore(final int score) {
        return score > FlyChicken.getInstance().scores.get(4).getPlayerPoints();
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

    public void updateAwards(int viewportWidth, int viewportHeight) {
        if (bird.getPosition().y - (viewportHeight / 2) > apple.getPosY() + apple.getHeight()) {
            apple.setPosX(getXRandomAxis(viewportWidth));
            apple.setPosY(getCurrentYAxis());
            apple.getAppleBounds().setPosition(apple.getPosX(), apple.getPosY());
        }

        if(bird.getPosition().y - (viewportHeight / 2) > star.getPosY() + star.getHeight()) {
            star.setPosX(getXRandomAxis(viewportWidth));
            star.setPosY(getCurrentYAxis());
            star.getStarBounds().setPosition(star.getPosX(), star.getPosY());
        }
    }

    public void updateBranches(int viewportHeight) {
        for (GameLogic.gameobjects.Branch branch : branches) {
            if (bird.getPosition().y- (viewportHeight / 2)  > branch.getPosRightBranch().y + branch.getHeight())
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

    public int getXRandomAxis(int viewportWidth) {
        int min = leftWall.getWidth();
        int max = viewportWidth-rightWall.getWidth();
        int x = rand.nextInt((max- min)+1)+min;
        return x;
    }

    public int getCurrentYAxis() {
        int min = 0 + (int)bird.getPosition().y;
        int max = FlyChicken.HEIGHT + (int)bird.getPosition().y;
        int y = bird.getPosY() + rand.nextInt((max - min)+1)+min;
        return y;
    }

    public void updateDist() {

        if((int)bird.getPosition().y - currDist >= 500) {
            score += 1;
            currDist = (int)bird.getPosition().y;
        }
    }

    public void setTimeSinceCollision(long time){
        timeSinceCollision = time;
    }
}
