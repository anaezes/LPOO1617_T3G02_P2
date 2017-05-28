package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

import GameInteraction.Interaction;
import GameLogic.EnumGameLevel;
import GameLogic.EnumGameState;
import GameLogic.GameMain;
import GameLogic.Score;
import GameLogic.gameobjects.Branch;

public class GameView implements Screen {
    private GameMain game;
    private float birdPosY;

    private FlyChicken gameMain;
    private OrthographicCamera cam;
    private FitViewport gamePort;
    private Hud hud;
    private Random rand;

    private Sound soundCoin, soundBiting;
    private Music musicGame;

    private ShapeRenderer shapeRenderer;
    private Interaction interaction;

    public GameView(FlyChicken mainGame, EnumGameLevel level, Interaction interaction) {
        this.interaction = interaction;
        this.gameMain = mainGame;
        interaction.setCatchBackKey(true);
        interaction.setInputProcessor(null);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, FlyChicken.WIDTH / 2, FlyChicken.HEIGHT / 2);
        shapeRenderer = new ShapeRenderer();

        hud = new Hud(gameMain.batch);
        gamePort = new FitViewport(FlyChicken.WIDTH/2, FlyChicken.HEIGHT/2, cam);
        game = new GameMain(level);

        createObjects();

        birdPosY = game.getGameBird().getPosition().y;

        rand = new Random();

        game.getGameBird().setValidPositionsX(game.getLeftWallPos1().x+game.getLeftWall().getTexture().getWidth(), game.getRightWallPos1().x,
                game.getWater().getPosY()+game.getWater().getWaterTexture().getHeight());

        soundCoin = Gdx.audio.newSound(Gdx.files.internal("coin.ogg"));
        soundBiting =  Gdx.audio.newSound(Gdx.files.internal("bittingApple.ogg"));

        if(FlyChicken.getInstance().getPrefs().getBoolean("music")) {
            musicGame = Gdx.audio.newMusic(Gdx.files.internal("musicGame.ogg"));
            musicGame.setLooping(true);
            musicGame.setVolume(0.05f);
            musicGame.play();
        }
    }

    private void createObjects() {
        game.createBird(FlyChicken.HEIGHT+400);
        game.createWater();
        game.createBranchs();
        game.createApple(50, -100);
        game.createStar(70, 400);
        game.createWalls(cam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(54/255f, 204/255f, 253/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameMain.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        gameMain.batch.setProjectionMatrix(cam.combined);
        gameMain.batch.begin();

        handleinput();

        updateObjects(delta);
        drawObjects();
        cam.update();
        checkCollisions();
        updateHud();

        gameMain.batch.end();

        //debug
        shapeRenderes();

        if (game.getState() == EnumGameState.Lose){
            int score = game.getScore();
            if(game.checkScore(score))
                saveScore(score);

            gameMain.setScreen(new GameMenu(gameMain));
            this.dispose();
        }
    }

    private void drawObjects() {
        drawBird();
        drawAwards();
        drawBranches();
        drawWater();
        drawWalls();
    }

    public void updateObjects(float delta) {
        updateBird(delta);
        cam.position.y = game.getGameBird().getPosition().y + game.getGameBird().getBirdTexture().getRegionHeight()/2;

        updateWalls(game.getGameBird().getPosition().y);
        game.updateAwards((int)cam.viewportWidth, (int)cam.viewportHeight);
        game.updateBranches((int)cam.viewportHeight);

        updateWater();
    }

    public void updateHud() {
        hud.updateHud(game.getLives(), game.getCurrTime(), game.getScore(), game.getEatenApples());
    }

    public void updateBird(float delta) {
        game.updateBirdPos(delta, interaction.getAccelerometerX());
    }


    public void drawBird() {
        gameMain.batch.draw(game.getGameBird().getBirdTexture(), game.getGameBird().getPosition().x, game.getGameBird().getPosition().y);
    }


    public void shapeRenderes() {

        cam.update();
        shapeRenderer.setProjectionMatrix(cam.combined);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(game.getGameBird().getBounds().x, game.getGameBird().getBounds().y, game.getGameBird().getBounds().radius);
        shapeRenderer.rect(game.getWater().getWaterBounds().getX(), game.getWater().getWaterBounds().getY(),
                game.getWater().getWaterBounds().getWidth(), game.getWater().getWaterBounds().getHeight());
        shapeRenderer.circle(game.getApple().getAppleBounds().x, game.getApple().getAppleBounds().x, game.getApple().getAppleBounds().radius);
        shapeRenderer.circle(game.getStar().getStarBounds().x, game.getStar().getStarBounds().x, game.getStar().getStarBounds().radius);

        for (Branch branch : game.getGameBranches()) {
            shapeRenderer.rect(branch.getBoundsLeftBranch().getX(), branch.getBoundsLeftBranch().y,
                    branch.getBoundsLeftBranch().getWidth(), branch.getBoundsLeftBranch().getHeight());
            shapeRenderer.rect(branch.getBoundsRightBranch().getX(), branch.getBoundsRightBranch().y,
                    branch.getBoundsRightBranch().getWidth(), branch.getBoundsRightBranch().getHeight());
        }

        shapeRenderer.end();
    }

    public void drawWater(){
        gameMain.batch.draw(game.getWater().getWaterTexture(), game.getWater().getPosX(), game.getWater().getPosY());

    }

    public void drawAwards(){
        gameMain.batch.draw(game.getApple().getAppleTexture(), game.getApple().getPosX(), game.getApple().getPosY());
        gameMain.batch.draw(game.getStar().getStarTexture(), game.getStar().getPosX(), game.getStar().getPosY());
    }


    public void drawWalls() {
        gameMain.batch.draw(game.getLeftWall().getTexture(), game.getLeftWallPos1().x, game.getLeftWallPos1().y);
        gameMain.batch.draw(game.getLeftWall().getTexture(), game.getLeftWallPos2().x, game.getLeftWallPos2().y);

        gameMain.batch.draw(game.getRightWall().getTexture(), game.getRightWallPos1().x, game.getRightWallPos1().y);
        gameMain.batch.draw(game.getRightWall().getTexture(), game.getRightWallPos2().x, game.getRightWallPos2().y);
    }

    public void drawBranches() {
        for (Branch branch : game.getGameBranches()) {
            gameMain.batch.draw(branch.getLeftBranch(), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            gameMain.batch.draw(branch.getRightBranch(), branch.getPosRightBranch().x, branch.getPosRightBranch().y);
        }
    }

    public void handleinput() {
        if(interaction.screenIsTouched()) {
            game.getGameBird().jump();
        }

        if (interaction.backKeyIsPressed()) {
            int score = game.getScore();
            if(game.checkScore(score))
                saveScore(score);

            this.dispose();
            gameMain.setScreen(new GameMenu(gameMain));
        }
    }

    public void updateWalls(float currentBirdPosY) {
        if(currentBirdPosY > birdPosY)
            updateMovementUp();
        else if(currentBirdPosY < birdPosY)
            updateMovementDown();

        this.birdPosY = currentBirdPosY;
    }

    void updateMovementDown() {
        if (cam.position.y+(cam.viewportHeight / 2) < game.getLeftWallPos1().y)
            game.getLeftWallPos1().add(0, - 2* game.getLeftWall().getTexture().getHeight());

        if (cam.position.y+(cam.viewportHeight / 2) < game.getLeftWallPos2().y)
            game.getLeftWallPos2().add(0, - 2* game.getLeftWall().getTexture().getHeight());

        if (cam.position.y + (cam.viewportHeight / 2) < game.getRightWallPos1().y )
            game.getRightWallPos1().add(0, - 2* game.getRightWall().getTexture().getHeight());

        if (cam.position.y + (cam.viewportHeight / 2) < game.getRightWallPos2().y )
            game.getRightWallPos2().add(0, - 2* game.getRightWall().getTexture().getHeight());
    }

    void updateMovementUp() {
            if (cam.position.y - (cam.viewportHeight / 2) > game.getLeftWallPos1().y + game.getLeftWall().getTexture().getHeight())
                game.getLeftWallPos1().add(0, game.getLeftWall().getTexture().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getLeftWallPos2().y + game.getLeftWall().getTexture().getHeight())
                game.getLeftWallPos2().add(0, game.getLeftWall().getTexture().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getRightWallPos1().y + game.getRightWall().getTexture().getHeight())
                game.getRightWallPos1().add(0, game.getRightWall().getTexture().getHeight() * 2);

            if (cam.position.y - (cam.viewportHeight / 2) > game.getRightWallPos2().y + game.getRightWall().getTexture().getHeight())
                game.getRightWallPos2().add(0, game.getRightWall().getTexture().getHeight() * 2);
    }

    public void checkCollisions(){
        if(game.checkCollisionsBranchs()) {
            float posX = game.getGameBird().getPosition().x + game.getGameBird().getBirdTexture().getRegionWidth()/2-game.getGameBird().getBirdStarsTexture().getWidth()/2;
            float posY = game.getGameBird().getPosition().y + 3*game.getGameBird().getBirdTexture().getRegionHeight()/4;
            gameMain.batch.draw(game.getGameBird().getBirdStarsTexture(), posX,  posY);
            if(FlyChicken.getInstance().getPrefs().getBoolean("vibration"))
                interaction.vibrate(500);
        }
        if(game.checkCollisionsWater()) {
            if(FlyChicken.getInstance().getPrefs().getBoolean("vibration"))
                interaction.vibrate(500);
            gameMain.setScreen(new GameMenu(gameMain));
        }

        if(game.checkAppleCollision()) {
            if(FlyChicken.getInstance().getPrefs().getBoolean("sound"))
                soundBiting.play();

            game.disposeApple();
            int x = game.getXRandomAxis((int)cam.viewportWidth);
            int y = game.getCurrentYAxis();
            game.createApple(x, y);

        }

        if(game.checkStarCollision()) {
            if(FlyChicken.getInstance().getPrefs().getBoolean("sound"))
                soundCoin.play();

            game.disposeStar();
            int x = game.getXRandomAxis((int)cam.viewportWidth);
            int y = game.getCurrentYAxis();
            game.createStar(x, y);

        }
    }

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
