package GameView;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by cristiana on 30-04-2017.
 */

public class ViewMain implements Screen {

    private FlyChicken game;
    private OrthographicCamera cam;
    private Viewport gamePort;
    private Hud hud;

    private static ViewMain instance = null;

    public ViewMain(FlyChicken game) {
        this.game = game;
        cam = new OrthographicCamera();
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, cam);
        hud = new Hud(game.batch);

    }

    public static ViewMain GetInstance(FlyChicken game) {
        if(instance == null) {
            instance = new ViewMain(game);
        }
        return instance;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
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
