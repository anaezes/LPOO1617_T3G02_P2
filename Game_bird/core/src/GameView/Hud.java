package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Class Hud
 * <br> Display information about the game (lives, scores, time elapsed since user start playing), during the game
 */
public class Hud {
    public Stage stage;
    private Viewport viewPort;
    private Texture apple;

    Label countTimeLabel;
    Label scoreLabel;
    Label timeLabel;
    Label livesLabel;
    Label worldLabel;
    Label chickenLabel;
    Label applecounterLabel;

    /**
     * Class Constructor Hud
     * @param sb SpriteBatch
     * <br> Create labels for hud and add them to the stage
     */
    public Hud(SpriteBatch sb) {
        viewPort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort, sb);

        apple = new Texture(Gdx.files.internal("littleapple.png"));
        TextureRegion appleRegion = new TextureRegion(apple);
        TextureRegionDrawable appleDraw = new TextureRegionDrawable(appleRegion);
        ImageButton appleBtn = new ImageButton(appleDraw);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        BitmapFont myfont = new BitmapFont();
        myfont.getData().scale(0.5f);

        timeLabel = new Label("Time", new Label.LabelStyle(myfont, Color.WHITE));
        countTimeLabel = new Label(String.format("%03d", 0), new Label.LabelStyle(myfont, Color.WHITE));

        chickenLabel = new Label("Fly Chicken..Fly", new Label.LabelStyle(myfont, Color.WHITE));
        scoreLabel = new Label(String.format("%06d", 0), new Label.LabelStyle(myfont, Color.WHITE));

        livesLabel = new Label(String.format("%01d", 3), new Label.LabelStyle(myfont, Color.WHITE));
        worldLabel = new Label("Lives", new Label.LabelStyle(myfont, Color.WHITE));

        applecounterLabel = new Label(String.format("%01d", 3), new Label.LabelStyle(myfont, Color.WHITE));



        table.add(chickenLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(appleBtn).expandX().padRight(30).padTop(8);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(livesLabel).expandX();
        table.add(countTimeLabel).expandX();
        table.add(applecounterLabel).expandX();

        stage.addActor(table);
    }

    /**
     * Update Hud with current:
     * @param lives game's lives
     * @param time  time elapsed since start playing
     * @param score score
     * @param eatenApples   eaten apples
     */
    public void updateHud(int lives, float time, int score, int eatenApples) {
        countTimeLabel.setText(String.format("%03d", (int)Math.round(time)));
        scoreLabel.setText(String.format("%06d", score));
        livesLabel.setText(String.format("%01d", lives));
        applecounterLabel.setText(String.format("%01d", eatenApples));
    }
}
