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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by cristiana on 30-04-2017.
 */

public class MainMenu implements Screen {


    private Viewport gamePort;
    private Stage stage;
    private Texture backGround;
    private ImageButton firstbtn, instr, scores, options, exit;
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
        btnTexture = new Texture(Gdx.files.internal("btn.png"));

        Texture playButton = new Texture(Gdx.files.internal("firstbtn.png"));
        TextureRegion playBtnRegion = new TextureRegion(playButton);
        TextureRegionDrawable playBtnDraw = new TextureRegionDrawable(playBtnRegion);


        TextureRegion btnRegion = new TextureRegion(btnTexture);
        TextureRegionDrawable btnDrawOptn = new TextureRegionDrawable(btnRegion);
        options = new ImageButton(btnDrawOptn);
        firstbtn = new ImageButton(btnDrawOptn);
        instr=new ImageButton(btnDrawOptn);
        scores=new ImageButton(btnDrawOptn);
        exit=new ImageButton(btnDrawOptn);


        stage.setDebugParentUnderMouse(true);
        firstbtn.setPosition(91,450);
        instr.setPosition(91, 360);
        options.setPosition(91, 180);
        exit.setPosition(91, 90);
        scores.setPosition(91,270);
        stage.addActor(firstbtn);
        stage.addActor(instr);
        stage.addActor(scores);
        stage.addActor(options);
        stage.addActor(exit);



        instr.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                onClickOInstructions();
                return true;
            }
        });

        options.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                onClickOptions();
                return true;
            }
        });

        scores.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                onClickScores();
                return true;
            }
        });
        exit.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                onClickExit();
                return true;
            }
        });

        firstbtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                onClickPlay();
                return true;
            }
        });
    }

    public void onClickPlay() {
        if(Gdx.input.isTouched()) {
            game.setScreen(PlayState.GetInstance());
            System.out.println("Play");
        }
    }

    public void onClickOptions() {
        if(Gdx.input.isTouched()) {
            System.out.println("Options");
        }
    }

    public void onClickOInstructions() {
        if(Gdx.input.isTouched()) {
            System.out.println("Instr");
        }
    }
    public void onClickScores() {
        if(Gdx.input.isTouched()) {
            System.out.println("Scores");
        }
    }
    public void onClickExit() {
        if(Gdx.input.isTouched()) {
            System.out.println("Sair");
            System.exit(0);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
     //   stage.getBatch().draw(backGround, 0,0, FlyChicken.WIDTH, FlyChicken.HEIGHT);
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

    }

    @Override
    public void dispose() {
        backGround.dispose();
        stage.dispose();
    }
}
