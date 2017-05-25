package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import GameLogic.Score;

public class ScoresMenu implements Screen{
    private Viewport gamePort;
    private Stage stage;
    private Texture backGround, btnreturn, table;
    private ImageButton goBack;
    private FlyChicken game;
    private Label record;

    public ScoresMenu(FlyChicken game) {
        this.game=game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);


        backGround = new Texture(Gdx.files.internal("bg.png"));
        btnreturn = new Texture(Gdx.files.internal("returnbtn.png"));
        table = new Texture(Gdx.files.internal("table.png"));
        TextureRegion returnBtnRegion = new TextureRegion(btnreturn);
        TextureRegionDrawable returnBtnDraw = new TextureRegionDrawable(returnBtnRegion);
        goBack = new ImageButton(returnBtnDraw);

        stage.setDebugParentUnderMouse(true);
        goBack.setPosition(50,50);
        stage.addActor(goBack);

        int total = 5;
        int totalRecords = FlyChicken.GetInstance().GetScores().size();
        if(totalRecords < 6) {
            total = totalRecords;
        }


        for(int i = 0; i<total; i++) {
        record = new Label(FlyChicken.GetInstance().GetScores().get(i).GetPlayerName() + " - "
                + String.format("%01d" ,FlyChicken.GetInstance().GetScores().get(i).GetPlayerPoints()),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        record.setPosition(FlyChicken.WIDTH/2 - record.getWidth()/2, FlyChicken.HEIGHT-100 - i * 40);
        stage.addActor(record);
        }


        goBack.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(goBack.isPressed()){
                    onClickBack();
                }
                return true;
            }
        });
    }

    public void onClickBack() {
        System.out.println("GoBack");
        game.setScreen(new MainMenu(game));

    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0,0, FlyChicken.WIDTH, FlyChicken.HEIGHT);
        stage.getBatch().draw(table, (FlyChicken.WIDTH-table.getWidth())/2,330, table.getWidth(), table.getHeight());
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
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
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void dispose() {
        backGround.dispose();
        stage.dispose();
    }
}
