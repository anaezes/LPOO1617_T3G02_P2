package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by cristiana on 07-05-2017.
 */

public class GameOverMenu implements Screen {
    private Viewport gamePort;
    private Stage stage;
    private Texture backGround,btnreturn;
    private FlyChicken game;
    private ImageButton btnGoBack, playAgain;

    public GameOverMenu(FlyChicken game){
        this.game=game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);

        backGround = new Texture(Gdx.files.internal("bg.png"));

        btnreturn = new Texture(Gdx.files.internal("exitBtn.png"));
        TextureRegion returnBtnRegion = new TextureRegion(btnreturn);
        TextureRegionDrawable returnBtnDraw = new TextureRegionDrawable(returnBtnRegion);
        btnGoBack = new ImageButton(returnBtnDraw);
        playAgain = new ImageButton(returnBtnDraw);

        playAgain.setPosition(FlyChicken.WIDTH/2-playAgain.getWidth()/2,
                FlyChicken.HEIGHT-playAgain.getHeight());
        btnGoBack.setPosition(FlyChicken.WIDTH/2-btnGoBack.getWidth()/2,
                FlyChicken.HEIGHT-btnGoBack.getHeight()*2);

        stage.addActor(playAgain);
        stage.addActor(btnGoBack);

        btnGoBack.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(btnGoBack.isPressed()){
                    onClickBack();
                }
                return true;
            }
        });

        playAgain.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(playAgain.isPressed()){
                    playagain();
                }
                return true;
            }
        });
    }

    public void onClickBack(){
        game.setScreen(new MainMenu(game));
    }

    public void playagain(){
        game.setScreen(new GameView(game));
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
