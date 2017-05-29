package GameView;

import com.badlogic.gdx.Gdx;
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


public class OptionsMenu extends Menu {
    private Texture backGround, btnreturn, options, rectangle;
    private Texture OnBtn, OffBtn;
    private ImageButton goBack, sound, vibration, music;
    private Label musicLabel, soundLabel, vibrationLabel;

    public OptionsMenu(FlyChicken game) {
        this.game = game;
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
        if (FlyChicken.getInstance().getPrefs().getBoolean("sound") == true)
            sound = new ImageButton(btnDrawOn, btnDrawOff, btnDrawOff);
        else
            sound = new ImageButton(btnDrawOff, btnDrawOn, btnDrawOn);

        if(FlyChicken.getInstance().getPrefs().getBoolean("music") == true)
            music = new ImageButton(btnDrawOn, btnDrawOff, btnDrawOff);
        else
            music = new ImageButton(btnDrawOff, btnDrawOn, btnDrawOn);

        if(FlyChicken.getInstance().getPrefs().getBoolean("vibration") == true)
            vibration = new ImageButton(btnDrawOn, btnDrawOff, btnDrawOff);
        else
            vibration = new ImageButton(btnDrawOff, btnDrawOn, btnDrawOn);

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

        goBack.setPosition(50,50);
        stage.addActor(goBack);
        music.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2+100, FlyChicken.HEIGHT-200);
        stage.addActor(music);
        sound.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2+100, FlyChicken.HEIGHT-300);
        stage.addActor(sound);
        vibration.setPosition(FlyChicken.WIDTH/2 - sound.getWidth()/2+100, FlyChicken.HEIGHT-400);
        stage.addActor(vibration);

        addListenners();
    }

    private void addListenners() {
        goBack.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(goBack.isTouchable()){
                    onClickBack();
                    return true;
                }
                return false;
            }
        });


            sound.addListener(new EventListener() {
                @Override
                public boolean handle(Event event) {
                    if (sound.isTouchable()){
                        onClickSound();
                        return true;
                    }
                    return false;
                }
            });

        music.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (music.isTouchable()){
                    onClickMusic();
                    return true;
                }
                return false;
            }
        });

        vibration.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (vibration.isTouchable()) {
                    onClickVibration();
                    return true;
                }
                return false;
            }
        });
    }


    public void onClickBack() {
        game.setScreen(new MainMenu(game));
    }

    public void onClickSound() {
        FlyChicken.getInstance().setPreferences("sound", !FlyChicken.getInstance().getPrefs().getBoolean("sound"));
    }

    public void onClickMusic() {
        FlyChicken.getInstance().setPreferences("music", !FlyChicken.getInstance().getPrefs().getBoolean("music"));

    }

    public void onClickVibration() {
        FlyChicken.getInstance().setPreferences("vibration", !FlyChicken.getInstance().getPrefs().getBoolean("vibration"));
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
}
