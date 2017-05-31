package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

import GameInteraction.GameInteraction;
import GameLogic.EnumGameLevel;

/**
 * Class that allow user chose game's level
 */
public class GameMenu extends Menu{
    private Texture btnreturn, btnLevelOne, btnLevelTwo, btnLevelThree;
    private ImageButton levelOneBtn, levelTwoBtn, levelThreeBtn;
    private ImageButton goBack;

    /**
     * Class Constructor Game Menu
     * @param game  - instance of main game
     * <br> Set background Texture
     * <br> Display buttons to choose level
     */
    public GameMenu(FlyChicken game){

        this.game = game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);

        backGround = new Texture(Gdx.files.internal("bg.png"));

        btnreturn = new Texture(Gdx.files.internal("returnbtn.png"));
        TextureRegion returnBtnRegion = new TextureRegion(btnreturn);
        TextureRegionDrawable returnBtnDraw = new TextureRegionDrawable(returnBtnRegion);
        goBack = new ImageButton(returnBtnDraw);

        goBack.setPosition(50,50);
        stage.addActor(goBack);

        goBack.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(goBack.isPressed()){
                    onClickBack();
                }
                return true;
            }
        });

        addButtonLevelOne();
        addButtonLevelTwo();
        addButtonLevelThree();
    }

    /**
     * Add Level One Button
     * <br> Set button position
     * <br> Add listeners to catch button click events
     */
    private void addButtonLevelOne() {
        btnLevelOne = new Texture(Gdx.files.internal("btnlevel1.png"));
        TextureRegion btn = new TextureRegion(btnLevelOne);
        TextureRegionDrawable btnDraw = new TextureRegionDrawable(btn);
        levelOneBtn = new ImageButton(btnDraw);

        levelOneBtn.setPosition(FlyChicken.WIDTH/2- levelOneBtn.getWidth()/2,
                FlyChicken.HEIGHT- levelOneBtn.getHeight());
        stage.addActor(levelOneBtn);

        levelOneBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(levelOneBtn.isPressed()){
                    levelOneClick();
                }
                return true;
            }
        });
    }

    /**
     * Add Level Two Button
     * <br> Set button position
     * <br> Add listeners to catch button click events
     */
    private void addButtonLevelTwo() {
        if(FlyChicken.getInstance().getScores().get(0).getPlayerPoints()  >= 1000) {
            btnLevelTwo = new Texture(Gdx.files.internal("btnlevel2.png"));
            TextureRegion btn = new TextureRegion(btnLevelTwo);
            TextureRegionDrawable btnDraw = new TextureRegionDrawable(btn);
            levelTwoBtn = new ImageButton(btnDraw);

            levelTwoBtn.addListener(new EventListener() {
                @Override
                public boolean handle(Event event) {
                    if(levelTwoBtn.isPressed()){
                        levelTwoClick();
                    }
                    return true;
                }
            });
        }
        else {
            btnLevelTwo = new Texture(Gdx.files.internal("btnlevel2block.png"));
            TextureRegion btn = new TextureRegion(btnLevelTwo);
            TextureRegionDrawable btnDraw = new TextureRegionDrawable(btn);
            levelTwoBtn = new ImageButton(btnDraw);
        }

        levelTwoBtn.setPosition(FlyChicken.WIDTH/2- levelTwoBtn.getWidth()/2,
                FlyChicken.HEIGHT- levelTwoBtn.getHeight()*2);
        stage.addActor(levelTwoBtn);

    }

    /**
     * Add Level One Three
     * <br> Set button position
     * <br> Add listeners to catch button click events
     */
    private void addButtonLevelThree() {
        if(FlyChicken.getInstance().getScores().get(0).getPlayerPoints()  >= 3000) {
            btnLevelThree = new Texture(Gdx.files.internal("btnlevel3.png"));
            TextureRegion btn = new TextureRegion(btnLevelThree);
            TextureRegionDrawable btnDraw = new TextureRegionDrawable(btn);
            levelThreeBtn = new ImageButton(btnDraw);

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
        else {
            btnLevelThree = new Texture(Gdx.files.internal("btnlevel3block.png"));
            TextureRegion btn = new TextureRegion(btnLevelThree);
            TextureRegionDrawable btnDraw = new TextureRegionDrawable(btn);
            levelThreeBtn = new ImageButton(btnDraw);
        }

        levelThreeBtn.setPosition(FlyChicken.WIDTH/2- levelThreeBtn.getWidth()/2,
                FlyChicken.HEIGHT- levelThreeBtn.getHeight()*3);
        stage.addActor(levelThreeBtn);
    }


    /**
     * Change the screen and corresponding level to be performed when user click level one button
     */
    public void levelOneClick(){
        game.setScreen(new GameView(game, EnumGameLevel.LevelOne, new GameInteraction()));
    }


    /**
     * Change the screen and corresponding level to be performed when user click level two button
     */
    public void levelTwoClick(){
        game.setScreen(new GameView(game, EnumGameLevel.LevelTwo, new GameInteraction()));
    }


    /**
     * Change the screen and corresponding level to be performed when user click level three button
     */
    public void levelThreeClick(){
        game.setScreen(new GameView(game, EnumGameLevel.LevelThree, new GameInteraction()));
        game.setScreen(new GameView(game, EnumGameLevel.LevelThree, new GameInteraction()));
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

    /**
     * Check if return button was pressed - go to main menu
     */
    public void handleinput() {
       if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
           game.setScreen(new MainMenu(game));
       }
    }

    /**
     * Check if return button's texture was pressed - go to main menu
     */
    public void onClickBack() {
        System.out.println("GoBack");
        game.setScreen(new MainMenu(game));
    }
}
