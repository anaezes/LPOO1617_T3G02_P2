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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class OptionsMenu implements Screen{
    private Viewport gamePort;
    private Stage stage;
    private Texture backGround, btnreturn, options, rectangle;
    private Texture OnBtn, OffBtn;
    private ImageButton goBack, sound, vibration, music;
    private FlyChicken game;
    private Label musicLabel, soundLabel, vibrationLabel;

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

        OnBtn = new Texture(Gdx.files.internal("onBtn.png"));
        OffBtn = new Texture(Gdx.files.internal("offBtn.png"));

        TextureRegion btnRegionOn = new TextureRegion(OnBtn);
        TextureRegionDrawable btnDrawOn = new TextureRegionDrawable(btnRegionOn);
        TextureRegion btnRegionOff = new TextureRegion(OffBtn);
        TextureRegionDrawable btnDrawOff = new TextureRegionDrawable(btnRegionOff);

        System.out.println(FlyChicken.getInstance().getPrefs().getBoolean("sound"));
        if (FlyChicken.getInstance().getPrefs().getBoolean("sound") == true) {
            sound = new ImageButton(btnDrawOn, btnDrawOff, btnDrawOff);
        } else {
            sound = new ImageButton(btnDrawOff, btnDrawOn, btnDrawOn);
        }

        if(FlyChicken.getInstance().getPrefs().getBoolean("music") == true) {
            music = new ImageButton(btnDrawOn, btnDrawOff, btnDrawOff);
        } else {
            music = new ImageButton(btnDrawOff, btnDrawOn, btnDrawOn);
        }

        if(FlyChicken.getInstance().getPrefs().getBoolean("vibration") == true) {
            vibration = new ImageButton(btnDrawOn, btnDrawOff, btnDrawOff);
        } else {
            vibration = new ImageButton(btnDrawOff, btnDrawOn, btnDrawOn);
        }

        BitmapFont myfont = new BitmapFont();
        myfont.getData().scale(1.05f);
        musicLabel = new Label("Music", new Label.LabelStyle(myfont, Color.WHITE));
        musicLabel.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2-100, FlyChicken.HEIGHT-200);
        stage.addActor(musicLabel);
        soundLabel  = new Label("Sound", new Label.LabelStyle(myfont, Color.WHITE));
        soundLabel.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2-100, FlyChicken.HEIGHT-300);
        stage.addActor(soundLabel);
        vibrationLabel = new Label("Vibration", new Label.LabelStyle(myfont, Color.WHITE));
        vibrationLabel.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2-100, FlyChicken.HEIGHT-400);
        stage.addActor(vibrationLabel);


        stage.setDebugParentUnderMouse(true);
        goBack.setPosition(50,50);
        stage.addActor(goBack);
        music.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2+100, FlyChicken.HEIGHT-200);
        stage.addActor(music);
        sound.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2+100, FlyChicken.HEIGHT-300);
        stage.addActor(sound);
        vibration.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2+100, FlyChicken.HEIGHT-400);
        stage.addActor(vibration);


        goBack.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(goBack.isPressed()){
                    onClickBack();
                    return true;
                }
                return false;
            }
        });

        soundAddListenner();
        musicAddListenner();
        vibrationAddListenner();
    }

    public void onClickBack() {
        System.out.println("GoBack");
        game.setScreen(new MainMenu(game));
        System.out.println("GoBack to main menu");

    }

    public void soundAddListenner() {
        sound.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (sound.isPressed()){
                    onClickSound();
                    return true;
                }
                return false;
            }
        });
    }

    public void musicAddListenner() {
        music.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (music.isPressed()){
                    onClickMusic();
                    return true;
                }
                return false;
            }
        });
    }

    public void vibrationAddListenner() {
        vibration.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (vibration.isPressed()) {
                    onClickVibration();
                    return true;
                }
                return false;
            }
        });
    }

    public void onClickSound() {
        System.out.println("SOUND pressed");
        boolean soundConfig = FlyChicken.getInstance().getPrefs().getBoolean("sound");
        FlyChicken.getInstance().setPreferences("sound", !soundConfig);
    }

    public void onClickMusic() {
        System.out.println("MUSIC pressed");
        boolean musicConfig = FlyChicken.getInstance().getPrefs().getBoolean("music");
        FlyChicken.getInstance().setPreferences("music", !musicConfig);

    }

    public void onClickVibration() {
        System.out.println("VIBRATION pressed");
        boolean vibraTionConfig = FlyChicken.getInstance().getPrefs().getBoolean("vibration");
        FlyChicken.getInstance().setPreferences("vibration", !vibraTionConfig);
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
