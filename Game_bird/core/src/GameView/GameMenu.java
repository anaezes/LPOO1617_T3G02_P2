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

import GameLogic.EnumGameLevel;

public class GameMenu implements Screen{
    private Viewport gamePort;
    private Stage stage;
    private Texture backGround, btnreturn, btnLevelOne, btnLevelTwo, btnLevelThree;
    private FlyChicken game;
    private ImageButton levelOneBtn, levelTwoBtn, levelThreeBtn;
    private ImageButton goBack;

    public GameMenu(FlyChicken game){

        this.game = game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);

        backGround = new Texture(Gdx.files.internal("bg.png"));

        btnreturn = new Texture(Gdx.files.internal("returnbtn.png"));
        TextureRegion returnBtnRegion = new TextureRegion(btnreturn);
        TextureRegionDrawable returnBtnDraw = new TextureRegionDrawable(returnBtnRegion);
        goBack = new ImageButton(returnBtnDraw);

        stage.setDebugParentUnderMouse(true);
        goBack.setPosition(50,50);
        stage.addActor(goBack);

        btnLevelOne = new Texture(Gdx.files.internal("btnlevel1.png"));
        TextureRegion btn = new TextureRegion(btnLevelOne);
        TextureRegionDrawable btnDraw = new TextureRegionDrawable(btn);
        levelOneBtn = new ImageButton(btnDraw);

        btnLevelTwo = new Texture(Gdx.files.internal("btnlevel2.png"));
        btn = new TextureRegion(btnLevelTwo);
        btnDraw = new TextureRegionDrawable(btn);
        levelTwoBtn = new ImageButton(btnDraw);

        btnLevelThree = new Texture(Gdx.files.internal("btnlevel3.png"));
        btn = new TextureRegion(btnLevelThree);
        btnDraw = new TextureRegionDrawable(btn);
        levelThreeBtn = new ImageButton(btnDraw);

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

    public void levelOneClick(){
        game.setScreen(new GameView(game, EnumGameLevel.LevelOne));
    }
    public void levelTwoClick(){
        game.setScreen(new GameView(game, EnumGameLevel.LevelTwo));
    }
    public void levelThreeClick(){
        game.setScreen(new GameView(game, EnumGameLevel.LevelThree));
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
           game.setScreen(new MainMenu(game));
       }
    }

    public void onClickBack() {
        System.out.println("GoBack");
        game.setScreen(new MainMenu(game));

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
