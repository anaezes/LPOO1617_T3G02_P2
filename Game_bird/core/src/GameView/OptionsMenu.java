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


public class OptionsMenu implements Screen{
    private Viewport gamePort;
    private Stage stage;
    private Texture backGround, btnreturn, options, rectangle, sound1, music1, vibration1;
    private ImageButton goBack, sound, vibration, music;
    private FlyChicken game;

    public OptionsMenu(FlyChicken game) {
        this.game=game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);

        options = new Texture(Gdx.files.internal("optionsBtn.png"));
        rectangle = new Texture(Gdx.files.internal("table2.png"));
        backGround = new Texture(Gdx.files.internal("bg.png"));
        btnreturn = new Texture(Gdx.files.internal("returnbtn.png"));
        TextureRegion returnBtnRegion = new TextureRegion(btnreturn);
        TextureRegionDrawable returnBtnDraw = new TextureRegionDrawable(returnBtnRegion);
        goBack = new ImageButton(returnBtnDraw);



        sound1 = new Texture(Gdx.files.internal("optionsBtn.png"));
        music1  = new Texture(Gdx.files.internal("optionsBtn.png"));
        vibration1  = new Texture(Gdx.files.internal("optionsBtn.png"));

        TextureRegion soundBtnRegion = new TextureRegion(sound1);
        TextureRegionDrawable soundBtnDraw = new TextureRegionDrawable(soundBtnRegion);
        TextureRegion musicBtnRegion = new TextureRegion(music1);
        TextureRegionDrawable musicBtnDraw = new TextureRegionDrawable(musicBtnRegion);
        TextureRegion vibraBtnRegion = new TextureRegion(vibration1);
        TextureRegionDrawable vibraBtnDraw = new TextureRegionDrawable(vibraBtnRegion);

        sound = new ImageButton(soundBtnDraw);
        music = new ImageButton(musicBtnDraw);
        vibration = new ImageButton(vibraBtnDraw);




        stage.setDebugParentUnderMouse(true);
        goBack.setPosition(50,50);
        stage.addActor(goBack);
        sound.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2, FlyChicken.HEIGHT-200);
        stage.addActor(sound);
        music.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2, FlyChicken.HEIGHT-350);
        stage.addActor(music);
        vibration.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2, FlyChicken.HEIGHT-500);
        stage.addActor(vibration);


        goBack.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(goBack.isPressed()){
                    onClickBack();
                }
                return true;
            }
        });

        sound.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (sound.isPressed()){
                    //onClickSound();
                    System.out.println("sound pressed");
                }
                return true;
            }
        });

        music.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (music.isPressed()){
                   // onClickMusic();
                    System.out.println("music pressed");
                }
                return true;
            }
        });

        vibration.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (vibration.isPressed()) {
                    System.out.println("vibra pressed");
                }
                return true;
            }
        });
    }

    public void onClickBack() {
        System.out.println("GoBack");
        game.setScreen(new MainMenu(game));

        System.out.println("GoBack to main menu");

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
        stage.getBatch().draw(options, FlyChicken.WIDTH/2-options.getWidth()/2, FlyChicken.HEIGHT-options.getHeight());
        stage.getBatch().draw(rectangle, FlyChicken.WIDTH/2- rectangle.getWidth()/2, FlyChicken.HEIGHT/2- rectangle.getHeight()/3);
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
