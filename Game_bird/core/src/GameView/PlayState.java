package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import GameLogic.Branch;
import GameLogic.GameMain;

public class PlayState implements Screen  {

    private static final int WALL_X_OFFSET = -40;
    private static final int BRANCH_SPACING = 125;
    private static final int BRANCH_COUNT = 10;

    private Texture leftWall, rightWall;
    private Vector2 leftWallPos1, leftWallPos2, rightWallPos1, rightWallPos2;

    private static PlayState instance = null;

    private GameMain game;
    private float birdPosY;

    private FlyChicken gameMain;
    private OrthographicCamera cam;
    private FitViewport gamePort;

    private static final String TAG = "Debug";

    public PlayState(FlyChicken mainGameObj) {

        Gdx.input.setCatchBackKey(true);

        this.gameMain = mainGameObj;
        cam = new OrthographicCamera();
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, cam);

        cam.setToOrtho(false, FlyChicken.WIDTH / 2, FlyChicken.HEIGHT / 2);

        game = GameMain.GetInstance();
        game.createBird(FlyChicken.WIDTH);

        birdPosY = game.getGameBird().getPosition().y;
        game.createWater();
        game.createBranchs(BRANCH_COUNT, BRANCH_SPACING);

        leftWall = new Texture("wallLeft.png");
        leftWallPos1 = new Vector2(WALL_X_OFFSET, cam.position.y - cam.viewportHeight/2);
        leftWallPos2 = new Vector2(WALL_X_OFFSET, (cam.position.x - cam.viewportWidth/2) + leftWall.getHeight());

        rightWall = new Texture("wallRight.png");
        rightWallPos1 = new Vector2(FlyChicken.WIDTH/2 - (rightWall.getWidth() + WALL_X_OFFSET), cam.position.y - cam.viewportHeight/2);
        rightWallPos2 = new Vector2(FlyChicken.WIDTH/2- (rightWall.getWidth() + WALL_X_OFFSET), (cam.position.x - cam.viewportWidth/2) + rightWall.getHeight());

        game.getGameBird().setValidPositionsX(leftWallPos1.x+leftWall.getWidth(), rightWallPos1.x, game.GetWater().getPosY()+game.GetWater().getWaterTexture().getHeight());
    }

    public static PlayState GetInstance() {
        if(instance == null) {
            instance = new PlayState(FlyChicken.GetInstance());
        }
        return instance;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(54/255f, 204/255f, 253/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMain.batch.setProjectionMatrix(cam.combined);
        gameMain.batch.begin();

        handleinput();
        updateWalls(game.getGameBird().getPosition().y);
        updateBranches();

        updateBird(delta);

        cam.position.y = game.getGameBird().getPosition().y + game.getGameBird().getBirdTexture().getHeight()/2;

        drawBird();
        drawBranches();
        drawWalls();

        gameMain.batch.draw(game.GetWater().getWaterTexture(), 0, 0);

        cam.update();

        if(game.checkCollisions()) {
            this.dispose();
            gameMain.setScreen(new PlayState(gameMain));
        }

        gameMain.batch.end();
    }


    public void updateBird(float delta) {
            game.getGameBird().update(delta);
    }


    public void updateBranches() {
        for (Branch branch : game.GetGameBranches())
            if(cam.position.y - (cam.viewportHeight/2) > branch.getPosRightBranch().y + branch.getLeftBranch().getHeight())
                branch.reposition(branch.getPosRightBranch().y + ((Branch.B_HEIGHT + BRANCH_SPACING) * BRANCH_COUNT));
    }


    public void drawBird() {
        gameMain.batch.draw(game.getGameBird().getBirdTexture(), game.getGameBird().getPosition().x, game.getGameBird().getPosition().y);
    }

    public void drawWalls() {
        gameMain.batch.draw(leftWall, leftWallPos1.x, leftWallPos1.y);
        gameMain.batch.draw(leftWall, leftWallPos2.x, leftWallPos2.y);

        gameMain.batch.draw(rightWall, rightWallPos1.x, rightWallPos1.y);
        gameMain.batch.draw(rightWall, rightWallPos2.x, rightWallPos2.y);
    }

    public void drawBranches() {
        for (Branch branch : game.GetGameBranches()) {
            gameMain.batch.draw(branch.getRightBranch(), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            gameMain.batch.draw(branch.getLeftBranch(), branch.getPosRightBranch().x, branch.getPosRightBranch().y);
        }
    }

    public void handleinput() {
        if(Gdx.input.justTouched()){
            game.getGameBird().jump();
            //game.getGameBird().setWeight(game.getGameBird().getWeight() + 10);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            gameMain.setScreen(new MainMenu(gameMain));
        }

    }

    public void updateWalls(float currentBirdPosY) {
        if(currentBirdPosY > birdPosY)
            updateMovementUp();
        else if(currentBirdPosY < birdPosY)
            updateMovementDown();

        this.birdPosY = currentBirdPosY;
    }

    void updateMovementDown() {
        if (cam.position.y+(cam.viewportHeight / 2) < leftWallPos1.y)
            leftWallPos1.add(0, - 2* leftWall.getHeight());

        if (cam.position.y+(cam.viewportHeight / 2) < leftWallPos2.y)
            leftWallPos2.add(0, - 2* leftWall.getHeight());

        if (cam.position.y + (cam.viewportHeight / 2) < rightWallPos1.y )
            rightWallPos1.add(0, - 2* rightWall.getHeight());

        if (cam.position.y + (cam.viewportHeight / 2) < rightWallPos2.y )
            rightWallPos2.add(0, - 2* rightWall.getHeight());
    }

    void updateMovementUp() {
            if (cam.position.y - (cam.viewportHeight / 2) > leftWallPos1.y + leftWall.getHeight())
                leftWallPos1.add(0, leftWall.getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > leftWallPos2.y + leftWall.getHeight())
                leftWallPos2.add(0, leftWall.getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > rightWallPos1.y + rightWall.getHeight())
                rightWallPos1.add(0, rightWall.getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > rightWallPos2.y + rightWall.getHeight())
                rightWallPos2.add(0, rightWall.getHeight() * 2);
    }



    @Override
    public void resize(int width, int height) {

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
