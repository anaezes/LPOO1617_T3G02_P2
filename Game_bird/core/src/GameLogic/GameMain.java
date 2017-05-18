package GameLogic;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import GameView.FlyChicken;
import GameView.GameOverMenu;


public class GameMain {

    private static final int WALL_X_OFFSET = -40;
    private static final int BRANCH_SPACING = 125;
    private static final int BRANCH_COUNT = 10;

    private int eatenApples;
    private Bird bird;
    private Array<Branch> branches;
    private Water water;
    private EnumGameLevel level;
    private Apple apple;
    private static GameMain instance = null;
    private  Random rand;

    private Texture leftWall, rightWall;
    private Vector2 leftWallPos1, leftWallPos2, rightWallPos1, rightWallPos2;

    private int lives;
    private float timeCount;
    private int score;

    public GameMain() {
        level = EnumGameLevel.LevelOne;
        eatenApples = 0;
        rand = new Random();

        lives = 3;
        timeCount = 0;
        score = 0;
    }

    public static GameMain GetInstance() {
        if(instance == null) {
            instance = new GameMain();
        }
        return instance;
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

    public float getCurrTime() {
        return timeCount;
    }


    public void createBird(int width) {
        bird = new Bird(100, width ,100);
    }

    public void createWater() {
        water = new Water(0, 0);
    }

    public void createApple(){
        this.apple = new Apple(50, 900);
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

    public boolean checkCollisionsBranchs() {

        for (int i=1; i < branches.size; i++){
            if(bird.getBounds().overlaps(branches.get(i).getBoundsLeftBranch()) ||
                    bird.getBounds().overlaps(branches.get(i).getBoundsRightBranch())) {
                if (lives != 0) {
                    updateLives(-1);
                    return true;
                }
            }
        }

    return false;
    }

    public boolean checkCollisionsWater() {

        if(bird.getBounds().overlaps(water.getWaterBounds())){
            if (lives != 0) {
                updateLives(-1);
                return true;
            }
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
        if(apple.getAppleBounds().overlaps(bird.getBounds())){
            this.eatenApples += 1;
            updateScore(50);
            return true;
        }
        return false;
    }

    public void updateApple(OrthographicCamera cam) {
        if (cam.position.y - (cam.viewportHeight / 2) > apple.getPosY() + apple.getAppleTexture().getHeight()) {
            int min = leftWall.getWidth() + 40;
            int max = (int)cam.viewportWidth-rightWall.getWidth() - 40;
            apple.setPosX(rand.nextInt((max- min)+1)+min);
            //apple.setPosX(rand.nextInt(max) + min);
            apple.setPosY((bird.getPosY() + (int)cam.position.y));
            apple.getAppleBounds().setPosition(apple.getPosX(), apple.getPosY());
        }
    }

    public void updateBranches(OrthographicCamera cam) {
        for (Branch branch : branches)
            if(cam.position.y - (cam.viewportHeight/2) > branch.getPosRightBranch().y + branch.getLeftBranch().getHeight())
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
}
