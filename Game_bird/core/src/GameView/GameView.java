package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

import GameLogic.Branch;
import GameLogic.EnumGameState;
import GameLogic.GameMain;

public class GameView implements Screen  {

    private static final int WATER_INCREMENT = 2;

    //private ViewMain gameview;

    private GameMain game;
    private float birdPosY;

    private FlyChicken gameMain;
    private OrthographicCamera cam;
    private FitViewport gamePort;
    private Hud hud;
    private Random rand;


    public GameView(FlyChicken mainGameObj) {
        Gdx.input.setCatchBackKey(true);
        this.gameMain = mainGameObj;

        Gdx.input.setInputProcessor(null);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, FlyChicken.WIDTH / 2, FlyChicken.HEIGHT / 2);
        hud = new Hud(gameMain.batch);

        gamePort = new FitViewport(FlyChicken.WIDTH/2, FlyChicken.HEIGHT/2, cam);
        game = new GameMain();
        game.createBird(FlyChicken.WIDTH);

        birdPosY = game.getGameBird().getPosition().y;
        game.createWater();
        game.createBranchs();
        game.createApple();
        game.createWalls(cam);
        rand = new Random();

        game.getGameBird().setValidPositionsX(game.getLeftWallPos1().x+game.getLeftWall().getWidth(), game.getRightWallPos1().x,
                game.GetWater().getPosY()+game.GetWater().getWaterTexture().getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(54/255f, 204/255f, 253/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMain.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        gameMain.batch.setProjectionMatrix(cam.combined);

        gameMain.batch.begin();

        handleinput();

        updateWalls(game.getGameBird().getPosition().y);
        game.updateApple(cam);
        game.updateBranches(cam);

        updateWater();

        updateBird(delta);

        cam.position.y = game.getGameBird().getPosition().y + game.getGameBird().getBirdTexture().getRegionHeight()/2;

        drawBird();
        drawApple();
        drawBranches();
        drawWater();
        drawWalls();

        cam.update();

        checkCollisions();

        updateHud();
        gameMain.batch.end();

        if (game.getState() == EnumGameState.Lose){
            gameMain.setScreen(new MainMenu(gameMain));
            this.dispose();
        }
    }

    public void updateHud() {
        hud.updateHud(game.getLives(), game.getCurrTime(), game.getScore(), game.getEatenApples());
    }

    public void updateBird(float delta) {
        game.getGameBird().update(delta);
    }


    public void drawBird() {
        gameMain.batch.draw(game.getGameBird().getBirdTexture(), game.getGameBird().getPosition().x, game.getGameBird().getPosition().y);
    }

    public void drawWater(){
        gameMain.batch.draw(game.GetWater().getWaterTexture(), game.GetWater().getPosX(), game.GetWater().getPosY());
    }

    public void drawApple(){
        gameMain.batch.draw(game.getApple().getAppleTexture(), game.getApple().getPosX(), game.getApple().getPosY());
    }


    public void drawWalls() {
        gameMain.batch.draw(game.getLeftWall(), game.getLeftWallPos1().x, game.getLeftWallPos1().y);
        gameMain.batch.draw(game.getLeftWall(), game.getLeftWallPos2().x, game.getLeftWallPos2().y);

        gameMain.batch.draw(game.getRightWall(), game.getRightWallPos1().x, game.getRightWallPos1().y);
        gameMain.batch.draw(game.getRightWall(), game.getRightWallPos2().x, game.getRightWallPos2().y);
    }

    public void drawBranches() {
        for (Branch branch : game.GetGameBranches()) {
            gameMain.batch.draw(branch.getRightBranch(), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            gameMain.batch.draw(branch.getLeftBranch(), branch.getPosRightBranch().x, branch.getPosRightBranch().y);
        }
    }

    public void handleinput() {
        if(Gdx.input.justTouched())
            game.getGameBird().jump();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
            gameMain.setScreen(new MainMenu(gameMain));
    }

    public void updateWalls(float currentBirdPosY) {
        if(currentBirdPosY > birdPosY)
            updateMovementUp();
        else if(currentBirdPosY < birdPosY)
            updateMovementDown();

        this.birdPosY = currentBirdPosY;
    }

    void updateMovementDown() {
        if (cam.position.y+(cam.viewportHeight / 2) < game.getLeftWallPos1().y)
            game.getLeftWallPos1().add(0, - 2* game.getLeftWall().getHeight());

        if (cam.position.y+(cam.viewportHeight / 2) < game.getLeftWallPos2().y)
            game.getLeftWallPos2().add(0, - 2* game.getLeftWall().getHeight());

        if (cam.position.y + (cam.viewportHeight / 2) < game.getRightWallPos1().y )
            game.getRightWallPos1().add(0, - 2* game.getRightWall().getHeight());

        if (cam.position.y + (cam.viewportHeight / 2) < game.getRightWallPos2().y )
            game.getRightWallPos2().add(0, - 2* game.getRightWall().getHeight());
    }

    void updateMovementUp() {
            if (cam.position.y - (cam.viewportHeight / 2) > game.getLeftWallPos1().y + game.getLeftWall().getHeight())
                game.getLeftWallPos1().add(0, game.getLeftWall().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getLeftWallPos2().y + game.getLeftWall().getHeight())
                game.getLeftWallPos2().add(0, game.getLeftWall().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getRightWallPos1().y + game.getRightWall().getHeight())
                game.getRightWallPos1().add(0, game.getRightWall().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getRightWallPos2().y + game.getRightWall().getHeight())
                game.getRightWallPos2().add(0, game.getRightWall().getHeight() * 2);
    }

    public void checkCollisions(){
        game.checkCollisionsBranchs();

        if(game.checkCollisionsWater())
            gameMain.setScreen(new GameView(gameMain));

        if(game.checkAppleCollision()) {
            game.disposeApple();
            game.createApple();
        }
    }


   public void updateWater(){
        game.GetWater().setPosY(game.GetWater().getPosY() + WATER_INCREMENT);
        game.GetWater().setWaterBoundsPosition(0, game.GetWater().getPosY());
   }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}