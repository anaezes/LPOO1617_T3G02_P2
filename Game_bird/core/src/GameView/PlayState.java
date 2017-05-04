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

/**
 * Created by cristiana on 30-04-2017.
 */

public class PlayState implements Screen {

    private static final int WALL_X_OFFSET = -40;
    private static final int BRANCH_SPACING = 125;
    private static final int BRANCH_COUNT = 10;
    private Texture background;

    private Texture leftWall, rightWall;
    private Vector2 leftWallPos1, leftWallPos2, rightWallPos1, rightWallPos2;

    private static PlayState instance = null;

    private Vector2 backPos1, backPos2;
    private GameMain game;

    private FlyChicken gameMain;
    private OrthographicCamera cam;
    private FitViewport gamePort;


    public PlayState(FlyChicken mainGameObj) {
        this.gameMain = mainGameObj;
        cam = new OrthographicCamera();
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, cam);

        cam.setToOrtho(false, FlyChicken.WIDTH / 2, FlyChicken.HEIGHT / 2);

        background = new Texture("simple_bg.png");
        backPos1 = new Vector2(0, cam.position.y - (cam.viewportHeight));
        backPos2 = new Vector2(0, cam.position.y - (cam.viewportHeight) + background.getHeight());

        game = GameMain.GetInstance();
        game.createBird();
        game.createBranchs(BRANCH_COUNT, BRANCH_SPACING);

        leftWall = new Texture("wallLeft.png");
        leftWallPos1 = new Vector2(WALL_X_OFFSET, cam.position.y - cam.viewportHeight/2);
        leftWallPos2 = new Vector2(WALL_X_OFFSET, (cam.position.x - cam.viewportWidth/2) + leftWall.getHeight());

        rightWall = new Texture("wallRight.png");
        rightWallPos1 = new Vector2(FlyChicken.WIDTH/2 - (rightWall.getWidth() + WALL_X_OFFSET), cam.position.y - cam.viewportHeight/2);
        rightWallPos2 = new Vector2(FlyChicken.WIDTH/2- (rightWall.getWidth() + WALL_X_OFFSET), (cam.position.x - cam.viewportWidth/2) + rightWall.getHeight());
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
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMain.batch.setProjectionMatrix(cam.combined);
        gameMain.batch.begin();

        handleinput();
        updateBackground();
        updateWalls();

        game.GetGameBird().update(delta);
        cam.position.y= game.GetGameBird().getPosition().y +80;

        gameMain.batch.draw(background, backPos1.x, backPos1.y);
        gameMain.batch.draw(background, backPos2.x, backPos2.y);

        gameMain.batch.draw(game.GetGameBird().getBirdTexture(), game.GetGameBird().getPosition().x, game.GetGameBird().getPosition().y);

        for (Branch branch : game.GetGameBranches()) {
            if(cam.position.y - (cam.viewportHeight/2) > branch.getPosRightBranch().y + branch.getLeftBranch().getHeight()){
                branch.reposition(branch.getPosRightBranch().y + ((Branch.B_HEIGHT + BRANCH_SPACING) * BRANCH_COUNT));
            }
            gameMain.batch.draw(branch.getRightBranch(), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            gameMain.batch.draw(branch.getLeftBranch(), branch.getPosRightBranch().x, branch.getPosRightBranch().y);

            System.out.print("b1: ");
            System.out.println(branch.getPosRightBranch().x);
        }

        gameMain.batch.draw(leftWall, leftWallPos1.x, leftWallPos1.y);
        gameMain.batch.draw(leftWall, leftWallPos2.x, leftWallPos2.y);

        gameMain.batch.draw(rightWall, rightWallPos1.x, rightWallPos1.y);
        gameMain.batch.draw(rightWall, rightWallPos2.x, rightWallPos2.y);

        cam.update();

        gameMain.batch.end();

    }

    public void handleinput() {
        if(Gdx.input.justTouched()){
            game.GetGameBird().jump();
            //game.GetGameBird().setWeight(game.GetGameBird().getWeight() + 10);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            System.out.println(Input.Keys.LEFT);
        }

    }

    public void updateBackground(){

        if(cam.position.y - (cam.viewportHeight/2) > backPos1.y + background.getHeight())
            backPos1.add(0, background.getHeight()*2);

        if(cam.position.y - (cam.viewportHeight/2) > backPos2.y + background.getHeight())
            backPos2.add(0, background.getHeight()*2);

    }

    public void updateWalls() {

        if(cam.position.y - (cam.viewportHeight / 2) > leftWallPos1.y + leftWall.getHeight())
            leftWallPos1.add(0, leftWall.getHeight()*2);

        if(cam.position.y - (cam.viewportHeight / 2) > leftWallPos2.y + leftWall.getHeight())
            leftWallPos2.add(0, leftWall.getHeight()*2);

        if(cam.position.y - (cam.viewportHeight / 2) > rightWallPos1.y + rightWall.getHeight())
            rightWallPos1.add(0, rightWall.getHeight()*2);

        if(cam.position.y - (cam.viewportHeight / 2) > rightWallPos2.y + rightWall.getHeight())
            rightWallPos2.add(0, rightWall.getHeight()*2);

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

/*
    @Override
    public void handleinput() {
        if(Gdx.input.justTouched()){
            game.GetGameBird().jump();
            //game.GetGameBird().setWeight(game.GetGameBird().getWeight() + 10);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            System.out.println(Input.Keys.LEFT);
        }

    }

    @Override
    public void update(float dt) {
        handleinput();
        updateBackground();

        game.GetGameBird().update(dt);
        cam.position.y= game.GetGameBird().getPosition().y +80;

        for (Branch branch : game.GetGameBranches()){
            if(cam.position.y - (cam.viewportHeight/2) > branch.getPosRightBranch().y + branch.getLeftBranch().getHeight()){
                branch.reposition(branch.getPosRightBranch().y + ((Branch.B_HEIGHT + BRANCH_SPACING) * BRANCH_COUNT));
            }
        }

        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //   sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);

        sb.draw(background, backPos1.x, backPos1.y);
        sb.draw(background, backPos2.x, backPos2.y);

        sb.draw(game.GetGameBird().getBirdTexture(), game.GetGameBird().getPosition().x, game.GetGameBird().getPosition().y);

        for (Branch branch : game.GetGameBranches()) {
            sb.draw(branch.getRightBranch(), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            sb.draw(branch.getLeftBranch(), branch.getPosRightBranch().x, branch.getPosRightBranch().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {

    }

    public void updateBackground(){

        if(cam.position.y - (cam.viewportHeight/2) > backPos1.y + background.getHeight())
            backPos1.add(0, background.getHeight()*2);

        if(cam.position.y - (cam.viewportHeight/2) > backPos2.y + background.getHeight())
            backPos2.add(0, background.getHeight()*2);

    }

    */

