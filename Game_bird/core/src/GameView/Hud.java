package GameView;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Hud {
    public Stage stage;
    private Viewport viewPort;

    Label countTimeLabel;
    Label scoreLabel;
    Label timeLabel;
    Label livesLabel;
    Label worldLabel;
    Label chickenLabel;

    public Hud(SpriteBatch sb) {
        viewPort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewPort, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countTimeLabel = new Label(String.format("%03d", 0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        chickenLabel = new Label("Fly Chicken..Fly", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", 0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        livesLabel = new Label(String.format("%01d", 3), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("Lives", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(chickenLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(livesLabel).expandX();
        table.add(countTimeLabel).expandX();

        stage.addActor(table);
    }

    public void updateHud(int lives, float time, int score) {
        countTimeLabel.setText(String.format("%03d", (int)Math.round(time)));
        scoreLabel.setText(String.format("%06d", score));
        livesLabel.setText(String.format("%01d", lives));
    }
}
