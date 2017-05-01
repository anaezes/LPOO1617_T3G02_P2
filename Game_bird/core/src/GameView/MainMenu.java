package GameView;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.xml.soap.Text;

import sun.applet.Main;

/**
 * Created by cristiana on 30-04-2017.
 */

public class MainMenu implements Screen {


    private Viewport gamePort;
    private Stage stage;
    private Texture backGround;
    private ImageButton playBtn;
    private FlyChicken game;
    private Texture btnTexture;

    private static MainMenu instance = null;

    public static MainMenu GetInstance() {
        if(instance == null) {
            instance = new MainMenu(FlyChicken.GetInstance());
        }
        return instance;
    }

    public MainMenu(FlyChicken game) {
        this.game = game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);
        Gdx.input.setInputProcessor(stage);
        backGround = new Texture(Gdx.files.internal("backg.png"));
        btnTexture = new Texture(Gdx.files.internal("bir.png"));

        TextureRegion regionBckg = new TextureRegion(backGround, 0 ,0);
        Image bckg = new Image(regionBckg);

        stage.addActor(bckg);

        Texture playButton = new Texture(Gdx.files.internal("playbtn.png"));
        TextureRegion playBtnRegion = new TextureRegion(playButton, 150, 150);
        TextureRegionDrawable playBtnDraw = new TextureRegionDrawable(playBtnRegion);
        playBtn = new ImageButton(playBtnDraw);

        playBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                onClickPlay();
                return true;
            }
        });

        TextureRegion btnRegion = new TextureRegion(btnTexture, 298, 88);
        TextureRegionDrawable btnDrawOptn = new TextureRegionDrawable(btnRegion);
        ImageButton options = new ImageButton(btnDrawOptn);


        options.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                onClickOptions();
                return true;
            }
        });


        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        //table.top();
        table.center();
        table.setFillParent(true);
        Label play = new Label("Play", font);
        table.add(playBtn).expandX();
        table.row();
        table.add(options).expandX().setActorHeight(150);

        stage.addActor(table);
    }

    public void onClickPlay() {
        if(Gdx.input.isTouched()) {
            game.setScreen(PlayState.GetInstance());
        }
    }

    public void onClickOptions() {
        if(Gdx.input.isTouched()) {
            System.out.println("Options");
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0,0, FlyChicken.WIDTH, FlyChicken.HEIGHT);
        stage.getBatch().end();

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

    }

    @Override
    public void dispose() {
        backGround.dispose();
        stage.dispose();
    }
}

/*
    @Override
    public void handleinput() {

        mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.unproject(mouse);

        if(Gdx.input.isTouched()){
            //   System.out.println(playbtn.getHeight());
            //   System.out.println(playbtn.getWidth());

            //   System.out.println(Gdx.input.getX());
            //   System.out.println(Gdx.input.getY());


            if (mouse.x>= 175 && mouse.x <=(175+playbtn.getWidth()) &&
                    mouse.y>=150 && mouse.y <=(150 + playbtn.getHeight())){
                System.out.println("carrega");
                gsm.set(PlayState.GetInstance(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, ViewMain.WIDTH, ViewMain.HEIGHT);
        sb.draw(playbtn, 175, 150);
        sb.end();

    }*/

