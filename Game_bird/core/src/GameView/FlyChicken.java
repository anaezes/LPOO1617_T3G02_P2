package GameView;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by cristiana on 30-04-2017.
 */

public class FlyChicken extends Game {

    public SpriteBatch batch;

    private static FlyChicken instance = null;

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Fly Chicken... FLY!";

    public static FlyChicken GetInstance() {
        if (instance == null) {
            instance = new FlyChicken();
        }
        return instance;
    }

    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(MainMenu.GetInstance());
    }

    @Override
    public void render () {
        super.render();
    }


    @Override
    public void dispose () {
    }
}
