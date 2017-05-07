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
    private ImageButton playBtn, instructionsBtn, scoresBtn, optionsBtn, exitBtn;
    private FlyChicken game;

    public MainMenu(FlyChicken game) {
        this.game = game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);
        backGround = new Texture(Gdx.files.internal("bg.png"));

        Texture tmp = new Texture(Gdx.files.internal("playBtn.png"));
        TextureRegion btnRegion = new TextureRegion(tmp);
        TextureRegionDrawable btnDraw = new TextureRegionDrawable(btnRegion);
        playBtn = new ImageButton(btnDraw);

        tmp = new Texture(Gdx.files.internal("instructionsBtn.png"));
        btnRegion = new TextureRegion(tmp);
        btnDraw = new TextureRegionDrawable(btnRegion);
        instructionsBtn = new ImageButton(btnDraw);

        tmp = new Texture(Gdx.files.internal("scoresBtn.png"));
        btnRegion = new TextureRegion(tmp);
        btnDraw = new TextureRegionDrawable(btnRegion);
        scoresBtn = new ImageButton(btnDraw);

        tmp = new Texture(Gdx.files.internal("optionsBtn.png"));
        btnRegion = new TextureRegion(tmp);
        btnDraw = new TextureRegionDrawable(btnRegion);
        optionsBtn = new ImageButton(btnDraw);

        tmp = new Texture(Gdx.files.internal("exitBtn.png"));
        btnRegion = new TextureRegion(tmp);
        btnDraw = new TextureRegionDrawable(btnRegion);
        exitBtn = new ImageButton(btnDraw);


        stage.setDebugParentUnderMouse(true);

        playBtn.setPosition(FlyChicken.WIDTH/2-playBtn.getWidth()/2,
                FlyChicken.HEIGHT-playBtn.getHeight());
        instructionsBtn.setPosition(FlyChicken.WIDTH/2-playBtn.getWidth()/2,
                FlyChicken.HEIGHT-playBtn.getHeight()*2);
        scoresBtn.setPosition(FlyChicken.WIDTH/2-playBtn.getWidth()/2,
                FlyChicken.HEIGHT-playBtn.getHeight()*3);
        optionsBtn.setPosition(FlyChicken.WIDTH/2-playBtn.getWidth()/2,
                FlyChicken.HEIGHT-playBtn.getHeight()*4);
        exitBtn.setPosition(FlyChicken.WIDTH/2-playBtn.getWidth()/2,
                FlyChicken.HEIGHT-playBtn.getHeight()*5);

        stage.addActor(playBtn);
        stage.addActor(instructionsBtn);
        stage.addActor(scoresBtn);
        stage.addActor(optionsBtn);
        stage.addActor(exitBtn);

        instructionsBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(instructionsBtn.isPressed()){
                    onClickOInstructions();
                }

                return false;
            }
        });

        optionsBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(optionsBtn.isPressed()){
                    onClickOptions();
                }

                return true;
            }
        });

        scoresBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(scoresBtn.isPressed()){
                    onClickScores();
                }

                return true;
            }
        });

        exitBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(exitBtn.isPressed()){
                    onClickExit();
                }

                return true;
            }
        });

        playBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(playBtn.isPressed()){
                    onClickPlay();
                }

                return true;
            }
        });
    }

    public void onClickPlay() {
            this.dispose();
            game.setScreen(new PlayState(game));
            System.out.println("Play");
    }

    public void onClickOptions() {
        game.setScreen(new OptionsMenu(game));
            System.out.println("Options");
    }

    public void onClickOInstructions() {
            game.setScreen(new InstructionMenu(game));
            System.out.println("Instr");
    }
    public void onClickScores() {
            game.setScreen(new ScoresMenu(game));
            System.out.println("Scores");
    }
    public void onClickExit() {
            System.out.println("Sair");
            System.exit(0);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0, 0, FlyChicken.WIDTH, FlyChicken.HEIGHT);
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
