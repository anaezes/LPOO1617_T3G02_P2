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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
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



        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

/*
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.center();
*/




     //   table.setOrigin(FlyChicken.WIDTH/2, FlyChicken.HEIGHT/2);
     //   table.setPosition(FlyChicken.WIDTH/2 - table.getWidth()/2, FlyChicken.HEIGHT/2-table.getHeight()/2);



    //    table.setBounds(0,0,FlyChicken.WIDTH,FlyChicken.HEIGHT);

     //   table.setTouchable(Touchable.enabled);



    /*    table.add(playBtn).fill();

        table.row();
        table.add(options).setActorHeight(88);

        stage.addActor(table);*/
        stage.setDebugParentUnderMouse(true);
        firstbtn.setPosition(Gdx.graphics.getWidth()/2-(firstbtn.getWidth()/2),Gdx.graphics.getHeight()-firstbtn.getHeight()-playButton.getHeight()-Gdx.graphics.getHeight()*0.2f);
        //firstbtn.setPosition(91,450);
        instr.setPosition(91, 360);
        options.setPosition(91, 180);
        exit.setPosition(91, 90);
        scores.setPosition(91,270);
        stage.addActor(firstbtn);
     /*   stage.addActor(instr);
        stage.addActor(scores);
        stage.addActor(options);
        stage.addActor(exit);
*/


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
            System.out.println("Options");}
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

