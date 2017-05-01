package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import GameLogic.Bird;
import GameLogic.Branch;
import GameLogic.GameMain;

/**
 * Created by cristiana on 30-04-2017.
 */

public class PlayState implements Screen {
    private static final int BRANCH_SPACING = 125;
    private static final int BRANCH_COUNT = 10;
    private Texture background;

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
        background = new Texture("stars.png");

        backPos1 = new Vector2(0, cam.position.y - (cam.viewportHeight));
        backPos2 = new Vector2(0, cam.position.y - (cam.viewportHeight) + background.getHeight());

        game = GameMain.GetInstance();
        game.createBird();
        game.createBranchs(BRANCH_COUNT, BRANCH_SPACING);

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

        game.GetGameBird().update(delta);
        cam.position.y= game.GetGameBird().getPosition().y +80;

        gameMain.batch.draw(background, backPos1.x, backPos1.y);
        gameMain.batch.draw(background, backPos2.x, backPos2.y);

        gameMain.batch.draw(game.GetGameBird().getBirdTexture(), game.GetGameBird().getPosition().x, game.GetGameBird().getPosition().y);

        for (Branch branch : game.GetGameBranches()) {
            if(cam.position.y - (cam.viewportHeight/2) > branch.getPosRightBranch().y + branch.getRightBranch().getHeight()){
                branch.reposition(branch.getPosRightBranch().y + ((Branch.B_HEIGHT + BRANCH_SPACING) * BRANCH_COUNT));
            }
            gameMain.batch.draw(branch.getLeftBranch(), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            gameMain.batch.draw(branch.getRightBranch(), branch.getPosRightBranch().x, branch.getPosRightBranch().y);
        }

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
            if(cam.position.y - (cam.viewportHeight/2) > branch.getPosRightBranch().y + branch.getRightBranch().getHeight()){
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
            sb.draw(branch.getLeftBranch(), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            sb.draw(branch.getRightBranch(), branch.getPosRightBranch().x, branch.getPosRightBranch().y);
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

