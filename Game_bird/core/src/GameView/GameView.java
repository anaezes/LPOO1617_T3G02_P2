package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

import GameInteraction.Interaction;
import GameLogic.EnumGameLevel;
import GameLogic.EnumGameState;
import GameLogic.GameMain;
import GameLogic.Score;
import GameLogic.gameobjects.Branch;

/**
 * Class GameView
 * <br> Class responsible for show and manage the game when user is playing
 */
public class GameView implements Screen {
    private GameMain game;
    private GameTextures gameTextures;
    private float birdPosY;

    private FlyChicken gameMain;
    private OrthographicCamera cam;
    private FitViewport gamePort;
    private Hud hud;
    private Random rand;

    private Sound soundCoin, soundBiting;
    private Music musicGame;

    private Interaction interaction;

    /**
     * Class Constructor GameView
     * @param mainGame      - instance of main game
     * @param level     - current game level
     * @param interaction    - interaction interface
     * <br> Create game objects
     */
    public GameView(FlyChicken mainGame, EnumGameLevel level, Interaction interaction) {
        this.interaction = interaction;
        this.gameMain = mainGame;
        this.gameTextures = new GameTextures(level);
        interaction.setCatchBackKey(true);
        interaction.setInputProcessor(null);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, FlyChicken.WIDTH / 2, FlyChicken.HEIGHT / 2);

        hud = new Hud(gameMain.batch);
        gamePort = new FitViewport(FlyChicken.WIDTH/2, FlyChicken.HEIGHT/2, cam);
        game = new GameMain(level);

        createObjects();

        birdPosY = game.getGameBird().getPosition().y;

        rand = new Random();

        game.getGameBird().setValidPositionsX(game.getLeftWallPos1().x+game.getLeftWall().getWidth(), game.getRightWallPos1().x,
                game.getWater().getPosY()+gameTextures.waterTexture.getHeight());

        soundCoin = Gdx.audio.newSound(Gdx.files.internal("coin.ogg"));
        soundBiting =  Gdx.audio.newSound(Gdx.files.internal("bittingApple.ogg"));

        if(FlyChicken.getInstance().getPrefs().getBoolean("music")) {
            musicGame = Gdx.audio.newMusic(Gdx.files.internal("musicGame.ogg"));
            musicGame.setLooping(true);
            musicGame.setVolume(0.1f);
            musicGame.play();
        }
    }

    /**
     * Create game objects
     */
    private void createObjects() {
        game.createBird(FlyChicken.HEIGHT+400, gameTextures.birdAnimation.getFrame().getRegionWidth(), gameTextures.birdAnimation.getFrame().getRegionHeight());
        game.createWater(gameTextures.waterTexture.getWidth(), gameTextures.waterTexture.getHeight());
        game.createBranchs(gameTextures.branchTextures.get(0).getWidth(), gameTextures.branchTextures.get(0).getHeight());
        game.createApple(50, -100, gameTextures.appleTexture.getWidth(), gameTextures.appleTexture.getHeight());
        game.createStar(70, 400, gameTextures.starTexture.getWidth(), gameTextures.starTexture.getHeight());
        game.createWalls(cam, gameTextures.wallTextures.get(0).getWidth(), gameTextures.wallTextures.get(0).getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(54/255f, 204/255f, 253/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMain.batch.setProjectionMatrix(cam.combined);
        gameMain.batch.begin();

        if (game.getState() == EnumGameState.Lose)
           doGameOver();

        handleinput();
        updateObjects(delta);
        drawObjects();
        cam.update();
        checkCollisions();
        updateHud();

        gameMain.batch.end();
        gameMain.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    /**
     * Stop music game
     * <br> Check final score
     * <br> Return to main menu
     */
    private void doGameOver() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int score = game.getScore();
        if(game.checkScore(score))
            saveScore(score);

        if(FlyChicken.getInstance().getPrefs().getBoolean("music"))
            musicGame.stop();
        gameMain.setScreen(new GameMenu(gameMain));
        this.dispose();
    }

    /**
     * Display image that allow user understand that he loose the game
     */
    private void displayGameOver() {
        gameMain.batch.draw(gameTextures.gameOver, cam.position.x-gameTextures.gameOver.getWidth()/2,
                game.getGameBird().getPosition().y + gameTextures.gameOver.getHeight()/2+50);
    }

    /**
     * Draw game objects
     */
    private void drawObjects() {
        drawBird();
        drawAwards();
        drawBranches();
        drawWater();
        drawWalls();
    }

    /**
     * Update game's objects
     * @param delta    delta time
     */
    public void updateObjects(float delta) {
        updateBird(delta);
        gameTextures.birdAnimation.update(delta);
        cam.position.y = game.getGameBird().getPosition().y + gameTextures.birdAnimation.getFrame().getRegionHeight()/2;
        updateWalls(game.getGameBird().getPosition().y);
        game.updateAwards((int)cam.viewportWidth, (int)cam.viewportHeight);
        game.updateBranches((int)cam.viewportHeight);
        updateWater();
    }

    /**
     * Update header of the game
     * <br> lives
     * <br> time passed since starting play
     * <br> score
     * <br> eaten apples
     */
    public void updateHud() {
        hud.updateHud(game.getLives(), game.getCurrTime(), game.getScore(), game.getEatenApples());
    }

    /**
     * Update bird position and velocity
     * @param delta - delta time
     */
    public void updateBird(float delta) {
        game.updateBirdPos(delta, interaction.getAccelerometerX());
    }


    /**
     * Draw bird
     */
    public void drawBird() {
        gameMain.batch.draw(gameTextures.birdAnimation.getFrame(), game.getGameBird().getPosition().x, game.getGameBird().getPosition().y);
    }


    /**
     * Draw water
     */
    public void drawWater(){
        gameMain.batch.draw(gameTextures.waterTexture, game.getWater().getPosX(), game.getWater().getPosY());

    }

    /**
     * Draw apples and stars
     */
    public void drawAwards(){
        gameMain.batch.draw(gameTextures.appleTexture, game.getApple().getPosX(), game.getApple().getPosY());
        gameMain.batch.draw(gameTextures.starTexture, game.getStar().getPosX(), game.getStar().getPosY());
    }


    /**
     * Draw walls
     */
    public void drawWalls() {
        gameMain.batch.draw(gameTextures.wallTextures.get(0), game.getLeftWallPos1().x, game.getLeftWallPos1().y);
        gameMain.batch.draw(gameTextures.wallTextures.get(0), game.getLeftWallPos2().x, game.getLeftWallPos2().y);

        gameMain.batch.draw(gameTextures.wallTextures.get(1), game.getRightWallPos1().x, game.getRightWallPos1().y);
        gameMain.batch.draw(gameTextures.wallTextures.get(1), game.getRightWallPos2().x, game.getRightWallPos2().y);
    }

    /**
     * Draw branches
     */
    public void drawBranches() {
        for (Branch branch : game.getGameBranches()) {
            gameMain.batch.draw(gameTextures.branchTextures.get(1), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            gameMain.batch.draw(gameTextures.branchTextures.get(0), branch.getPosRightBranch().x, branch.getPosRightBranch().y);
        }
    }

    /**
     * Handle input from user
     * <br> touch to bird jump
     * <br> check if return button was pressed
     */
    public void handleinput() {
        if(interaction.screenIsTouched())
            game.getGameBird().jump();

        if (interaction.backKeyIsPressed()) {
            int score = game.getScore();
            if(game.checkScore(score))
                saveScore(score);
            if(FlyChicken.getInstance().getPrefs().getBoolean("music"))
                musicGame.stop();
            this.dispose();
            gameMain.setScreen(new GameMenu(gameMain));
        }
    }

    /**
     * Update wall's textures
     * @param currentBirdPosY   bird's y coordinate
     */
    public void updateWalls(float currentBirdPosY) {
        if(currentBirdPosY > birdPosY)
            updateMovementUp();
        else if(currentBirdPosY < birdPosY)
            updateMovementDown();

        this.birdPosY = currentBirdPosY;
    }

    /**
     * Update walls when bird is falling down
     */
    void updateMovementDown() {
        if (cam.position.y+(cam.viewportHeight / 2) < game.getLeftWallPos1().y)
            game.getLeftWallPos1().add(0, - 2* game.getLeftWall().getHeight());

        if (cam.position.y+(cam.viewportHeight / 2) < game.getLeftWallPos2().y)
            game.getLeftWallPos2().add(0, - 2* game.getLeftWall().getHeight());

        if (cam.position.y + (cam.viewportHeight / 2) < game.getRightWallPos1().y )
            game.getRightWallPos1().add(0, - 2* game.getRightWall().getHeight());

        if (cam.position.y + (cam.viewportHeight / 2) < game.getRightWallPos2().y )
            game.getRightWallPos2().add(0, - 2* game.getRightWall().getHeight());
    }

    /**
     * Update walls when bird is moving up
     */
    void updateMovementUp() {
            if (cam.position.y - (cam.viewportHeight / 2) > game.getLeftWallPos1().y + game.getLeftWall().getHeight())
                game.getLeftWallPos1().add(0, game.getLeftWall().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getLeftWallPos2().y + game.getLeftWall().getHeight())
                game.getLeftWallPos2().add(0, game.getLeftWall().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getRightWallPos1().y + game.getRightWall().getHeight())
                game.getRightWallPos1().add(0, game.getRightWall().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getRightWallPos2().y + game.getRightWall().getHeight())
                game.getRightWallPos2().add(0, game.getRightWall().getHeight() * 2);
    }

    /**
     * Check bird's collision with game objects; water, branches, apples and stars
     * <br> Do alterations like stop music and sound, activate vibration when lose in water
     */
    public void checkCollisions(){
        if(game.checkCollisionsBranchs()) {
            float posX = game.getGameBird().getPosition().x + game.getGameBird().getWidth()/2-gameTextures.birdStars.getWidth()/2;
            float posY = game.getGameBird().getPosition().y + 3*game.getGameBird().getHeight()/4;
            gameMain.batch.draw(gameTextures.birdStars, posX,  posY);
            if(FlyChicken.getInstance().getPrefs().getBoolean("vibration"))
                interaction.vibrate(500);
        }
        if(game.checkCollisionsWater()) {
            if(FlyChicken.getInstance().getPrefs().getBoolean("vibration"))
                interaction.vibrate(500);
        }

        if(game.checkAppleCollision()) {
            if(FlyChicken.getInstance().getPrefs().getBoolean("sound"))
                soundBiting.play();
            createApple();
        }

        if(game.checkStarCollision()) {
            if(FlyChicken.getInstance().getPrefs().getBoolean("sound"))
                soundCoin.play();
            createStar();
        }

        if(game.getState() == EnumGameState.Lose)
            displayGameOver();
    }

    /**
     * Create game's apples
     */
    private void createApple() {
        game.disposeApple();
        int x = game.getXRandomAxis((int)cam.viewportWidth);
        int y = game.getCurrentYAxis();
        game.createApple(x, y, gameTextures.appleTexture.getWidth(), gameTextures.appleTexture.getHeight());
    }

    /**
     * Create game's stars
     */
    private void createStar() {
        game.disposeStar();
        int x = game.getXRandomAxis((int)cam.viewportWidth);
        int y = game.getCurrentYAxis();
        game.createStar(x, y, gameTextures.starTexture.getWidth(), gameTextures.starTexture.getHeight());
    }

    /**
     * Save scores
     * <br> Popup to ask user's name
     * <br> Save scores
     * @param score     score
     */
    public void saveScore(final int score) {
        Gdx.input.getTextInput(new Input.TextInputListener() {
            @Override
            public void input(String text) {
                Score playerScore = new Score(text, score);
                FlyChicken.getInstance().AddScore(playerScore);
            }

            @Override
            public void canceled() {
                Score playerScore = new Score("Anonymous", score);
                FlyChicken.getInstance().AddScore(playerScore);
            }
        }, "New High Score", "", "Your Name");
    }

    /**
     * Update water's position
     */
   public void updateWater(){
        game.getWater().setPosY(game.getWater().getPosY() + game.getWater().getWaterIncrement());
        game.getWater().setWaterBoundsPosition(0, game.getWater().getPosY());
   }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
    }
}
