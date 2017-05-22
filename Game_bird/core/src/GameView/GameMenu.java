package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class GameMenu implements Screen{
    private Viewport gamePort;
    private Stage stage;
    private Texture backGround, btn;
    private FlyChicken game;
    private ImageButton levelOneBtn, levelTwoBtn, levelThreeBtn;

    public GameMenu(FlyChicken game){

        this.game = game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);

        backGround = new Texture(Gdx.files.internal("bg.png"));

        btn = new Texture(Gdx.files.internal("btn.png"));

        TextureRegion returnBtnRegion = new TextureRegion(btn);
        TextureRegionDrawable returnBtnDraw = new TextureRegionDrawable(returnBtnRegion);

        levelOneBtn = new ImageButton(returnBtnDraw);
        levelTwoBtn = new ImageButton(returnBtnDraw);
        levelThreeBtn = new ImageButton(returnBtnDraw);

        levelOneBtn.setPosition(FlyChicken.WIDTH/2- levelOneBtn.getWidth()/2,
                FlyChicken.HEIGHT- levelOneBtn.getHeight());
        levelTwoBtn.setPosition(FlyChicken.WIDTH/2- levelTwoBtn.getWidth()/2,
                FlyChicken.HEIGHT- levelTwoBtn.getHeight()*2);
        levelThreeBtn.setPosition(FlyChicken.WIDTH/2- levelThreeBtn.getWidth()/2,
                FlyChicken.HEIGHT- levelThreeBtn.getHeight()*3);

        stage.addActor(levelOneBtn);
        stage.addActor(levelTwoBtn);
        stage.addActor(levelThreeBtn);

        levelOneBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(levelOneBtn.isPressed()){
                    levelOneClick();
                }
                return true;
            }
        });

        levelTwoBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(levelTwoBtn.isPressed()){
                    levelTwoClick();
                }
                return true;
            }
        });

        levelThreeBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(levelThreeBtn.isPressed()){
                    levelThreeClick();
                }
                return true;
            }
        });
    }

    public void levelOneClick(){
        game.setScreen(new GameView(game));
    }
    public void levelTwoClick(){
        game.setScreen(new GameView(game));
    }
    public void levelThreeClick(){
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

        handleinput();
    }

    public void handleinput() {
       if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
           this.dispose();
           game.setScreen(new MainMenu(game));
       }
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
