package GameView;

import com.badlogic.gdx.Gdx;
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

import GameLogic.EnumGameLevel;

/**
 * Class InstructionMenu
 * <br> Class that manage Instructions Menu
 */
public class InstructionMenu extends Menu {
    private Texture btnreturn, instructions, rectInstructions;
    private ImageButton goBack;
    private GameTextures gameTexturesOne, gameTexturesTwo, gameTexturesThree;

    private static InstructionMenu instance = null;

    public static InstructionMenu getInstance() {
        if(instance == null) {
            instance = new InstructionMenu(FlyChicken.getInstance());
        }
        return instance;
    }

    /**
     * Class Constructor InstructionMenu
     * @param game  instance of main game
     *
     */
    public InstructionMenu(FlyChicken game) {
        this.game=game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);

        backGround = new Texture(Gdx.files.internal("bg.png"));
        instructions = new Texture(Gdx.files.internal("instructionsBtn.png"));
        rectInstructions = new Texture(Gdx.files.internal("tableInstruc.png"));
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

        this.gameTexturesOne = new GameTextures(EnumGameLevel.LevelOne);
        this.gameTexturesTwo = new GameTextures(EnumGameLevel.LevelTwo);
        this.gameTexturesThree = new GameTextures(EnumGameLevel.LevelThree);
    }

        public void onClickBack() {
                System.out.println("GoBack");
                game.setScreen(new MainMenu(game));

                System.out.println("GoBack to main menu");
        }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0,0, FlyChicken.WIDTH, FlyChicken.HEIGHT);
        stage.getBatch().draw(instructions, FlyChicken.WIDTH/2-instructions.getWidth()/2, FlyChicken.HEIGHT-instructions.getHeight());
        stage.getBatch().draw(rectInstructions, FlyChicken.WIDTH/2- rectInstructions.getWidth()/2, FlyChicken.HEIGHT/2- rectInstructions.getHeight()/3);
        gameTexturesOne.birdAnimation.update(delta);
        gameTexturesTwo.birdAnimation.update(delta);
        gameTexturesThree.birdAnimation.update(delta);
        stage.getBatch().draw(gameTexturesOne.birdAnimation.getFrame(), FlyChicken.WIDTH/2 - 2*gameTexturesOne.birdAnimation.getFrame().getRegionWidth(),
                FlyChicken.HEIGHT/2-2*gameTexturesOne.birdAnimation.getFrame().getRegionHeight()-25);
        stage.getBatch().draw(gameTexturesTwo.birdAnimation.getFrame(), FlyChicken.WIDTH/2 - gameTexturesTwo.birdAnimation.getFrame().getRegionWidth()/2+5,
                FlyChicken.HEIGHT/2-2*gameTexturesTwo.birdAnimation.getFrame().getRegionHeight());
        stage.getBatch().draw(gameTexturesThree.birdAnimation.getFrame(), FlyChicken.WIDTH/2 + 2*gameTexturesThree.birdAnimation.getFrame().getRegionWidth()/2,
                FlyChicken.HEIGHT/2-2*gameTexturesThree.birdAnimation.getFrame().getRegionHeight()-15);
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }
}
