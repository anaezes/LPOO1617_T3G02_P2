package GameLogic;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import GameView.FlyChicken;


public class GameMain {

    private static final int WALL_X_OFFSET = -40;
    private static final int BRANCH_SPACING = 125;
    private static final int BRANCH_COUNT = 10;

    private EnumGameLevel level;
    private EnumGameState state;

    private int eatenApples;
    private Bird bird;
    private Body birdBody;

    private Array<Branch> branches;

    private Water water;
    private Apple apple;
    private static GameMain instance = null;
    private  Random rand;

    private Texture leftWall, rightWall;
    private Vector2 leftWallPos1, leftWallPos2, rightWallPos1, rightWallPos2;

    private int lives;
    private long timeCount;
    private long currTime;
    private int score;
    private long timeSinceCollision;


    public GameMain() {
        level = EnumGameLevel.LevelOne;
        state = EnumGameState.Running;
        eatenApples = 0;
        rand = new Random();

        lives = 3;
        timeCount = 0;
        currTime = System.currentTimeMillis();
        score = 0;
        timeSinceCollision = System.nanoTime();
    }

    public void updateTime(float dt) {
        timeCount+=dt;
    }

    public void updateLives(int value) {
            lives+=value;
    }

    public void updateScore(int sc) {
        score+=sc;
    }

    public int getScore() {
        return score;
    }

    public void setScore( int score1){
        score=score1;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int live) {
        lives = live;
    }

    public long getCurrTime() {
        timeCount = (System.currentTimeMillis() - currTime)/1000;
        System.out.println("TIME:"+ timeCount);
        return timeCount;
    }


    public void createBird(int width) {
        bird = new Bird(100, width ,100);
    }

    public void createWater() {
        water = new Water(0, 0);
    }

    public void createApple(int x, int y){
        // x = 50 y = -100
        this.apple = new Apple(x, y);
    }

    public void createBranchs() {
        branches = new Array<Branch>();
        for (int i=1; i<BRANCH_COUNT; i++){
            branches.add(new Branch(0,i* (BRANCH_SPACING + Branch.B_HEIGHT)));
        }
    }

    public void createWalls(OrthographicCamera cam) {
        leftWall = new Texture("wallLeft.png");
        leftWallPos1 = new Vector2(WALL_X_OFFSET, cam.position.y - cam.viewportHeight/2);
        leftWallPos2 = new Vector2(WALL_X_OFFSET, (cam.position.x - cam.viewportWidth/2) + leftWall.getHeight());

        rightWall = new Texture("wallRight.png");
        rightWallPos1 = new Vector2(FlyChicken.WIDTH/2 - (rightWall.getWidth() + WALL_X_OFFSET), cam.position.y - cam.viewportHeight/2);
        rightWallPos2 = new Vector2(FlyChicken.WIDTH/2- (rightWall.getWidth() + WALL_X_OFFSET), (cam.position.x - cam.viewportWidth/2) + rightWall.getHeight());

    }

    public EnumGameLevel GetCurrentGameLevel() {
        return level;
    }

    public void SetGameLevel(EnumGameLevel level) {
        this.level = level;
    }

    public Bird getGameBird() {
        return bird;
    }

    public Apple getApple() {
        return apple;
    }

    public void disposeApple() {
        this.apple = null;
    }

    public Water GetWater() {
        return water;
    }

    public Array<Branch> GetGameBranches() {
        return branches;
    }

    public void updateState() {
        System.out.print("LIVES:    " + lives);

        if(lives < 0)
            state = EnumGameState.Lose;
    }

    public EnumGameState getState() {
        return state;
    }

    public boolean checkCollisionsBranchs() {
        long delta_time =(System.nanoTime() - timeSinceCollision)/ 1000000;
        TimeUnit.SECONDS.convert(delta_time, TimeUnit.NANOSECONDS);
        if(delta_time <= 200) {
            return false;
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

    public void updateApple(OrthographicCamera cam) {
        if (cam.position.y - (cam.viewportHeight / 2) > apple.getPosY() + apple.getAppleTexture().getHeight()) {
            apple.setPosX(GetXRandomAxis(cam));
            apple.setPosY(GetCurrentYAxis(cam));
            apple.getAppleBounds().setPosition(apple.getPosX(), apple.getPosY());
        }
    }

    public void updateBranches(OrthographicCamera cam) {
        for (Branch branch : branches)
            if(cam.position.y - (cam.viewportHeight/2) > branch.getPosRightBranch().y + branch.getRightBranch().getHeight())
                branch.reposition(branch.getPosRightBranch().y + ((Branch.B_HEIGHT + BRANCH_SPACING) * BRANCH_COUNT));
    }

    public Texture getLeftWall(){
        return leftWall;
    }

    public Texture getRightWall(){
        return rightWall;
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

    public int GetXRandomAxis(OrthographicCamera cam) {
        int min = leftWall.getWidth();
        int max = (int)cam.viewportWidth-rightWall.getWidth();
        int x = rand.nextInt((max- min)+1)+min;
        return x;
    }

    public int GetCurrentYAxis(OrthographicCamera cam) {
        int y = bird.getPosY() + (int)cam.position.y;
        return y;
    }
}
