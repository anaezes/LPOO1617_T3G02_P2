package GameView;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by cristiana on 30-04-2017.
 */

public class ViewMain extends ApplicationAdapter{
    private SpriteBatch batch;
    private GameStateManager gsm;

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Fly Chicken... FLY!";

    @Override
    public void create () {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MainMenu(gsm));
    }

    @Override
    public void render () {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }


    @Override
    public void dispose () {
        batch.dispose();
        //img.dispose();
    }
}
