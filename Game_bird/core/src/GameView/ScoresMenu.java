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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Class ScoresMenu
 */
public class ScoresMenu extends Menu{
    private Texture btnreturn, tableScore, rectangle;
    private ImageButton goBack;
    private Table table;
    private Label name;
    private Label score;


    /**
     * Class Constructor ScoresMenu
     * @param game  instance of main game
     */
    public ScoresMenu(FlyChicken game) {
        this.game=game;
        gamePort = new FitViewport(FlyChicken.WIDTH, FlyChicken.HEIGHT, new OrthographicCamera());
        stage = new Stage(gamePort, game.batch);
        backGround = new Texture(Gdx.files.internal("bg.png"));
        btnreturn = new Texture(Gdx.files.internal("returnbtn.png"));
        tableScore = new Texture(Gdx.files.internal("scoresBtn.png"));
        rectangle = new Texture(Gdx.files.internal("table2.png"));

        TextureRegion returnBtnRegion = new TextureRegion(btnreturn);
        TextureRegionDrawable returnBtnDraw = new TextureRegionDrawable(returnBtnRegion);
        goBack = new ImageButton(returnBtnDraw);

        goBack.setPosition(50,50);
        stage.addActor(goBack);
        btnAddListener();

        addTableScores();
    }

    private void btnAddListener() {
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
    }

    /**
     * Return to main menu
     */
    public void onClickBack() {
        System.out.println("GoBack");
        game.setScreen(new MainMenu(game));

    }

    /**
     * Display top 5 scores 
     */
    public void addTableScores() {
        int total = 5;
        int totalRecords = FlyChicken.getInstance().getScores().size();
        if(totalRecords < 6) {
            total = totalRecords;
        }

        BitmapFont myfont = new BitmapFont();
        myfont.getData().scale(1.05f);

        for(int i = 0; i < total; i++) {
            name = new Label(FlyChicken.getInstance().getScores().get(i).getPlayerName(), new Label.LabelStyle(myfont, Color.WHITE));
            score = new Label(String.format("%01d", FlyChicken.getInstance().getScores().get(i).getPlayerPoints()), new Label.LabelStyle(myfont, Color.WHITE));
            name.setPosition(FlyChicken.WIDTH / 2 - name.getWidth() / 2 - 70, FlyChicken.HEIGHT - 200 - i * 70);
            score.setPosition(FlyChicken.WIDTH / 2 - score.getWidth() / 2 + 110, FlyChicken.HEIGHT - 200 - i * 70);
            stage.addActor(name);
            stage.addActor(score);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0,0, FlyChicken.WIDTH, FlyChicken.HEIGHT);
        stage.getBatch().draw(tableScore, FlyChicken.WIDTH/2-tableScore.getWidth()/2, FlyChicken.HEIGHT-tableScore.getHeight());
        stage.getBatch().draw(rectangle, FlyChicken.WIDTH/2- rectangle.getWidth()/2, FlyChicken.HEIGHT/2- rectangle.getHeight()/3);
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }
}
