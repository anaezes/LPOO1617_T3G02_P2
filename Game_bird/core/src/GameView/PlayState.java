package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import GameLogic.Bird;
import GameLogic.Branch;
import GameLogic.GameMain;

/**
 * Created by cristiana on 30-04-2017.
 */

public class PlayState extends State {
    private static final int BRANCH_SPACING = 125;
    private static final int BRANCH_COUNT = 10;
    private Texture background;

    private Vector2 backPos1, backPos2;
    private GameMain game;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, ViewMain.WIDTH/2, ViewMain.HEIGHT/2);
        background = new Texture("stars.png");

        backPos1 = new Vector2(0,cam.position.y- (cam.viewportHeight));
        backPos2 = new Vector2(0, cam.position.y- (cam.viewportHeight)+ background.getHeight());

        game = new GameMain();
        game.createBird();
        game.createBranchs(BRANCH_COUNT, BRANCH_SPACING);

    }

    @Override
    public void handleinput() {
        if(Gdx.input.justTouched()){
            game.GetGameBird().jump();
            //game.GetGameBird().setWeight(game.GetGameBird().getWeight() + 10);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            System.out.println(Input.Keys.LEFT);
        }

    }

    @Override
    public void update(float dt) {
        handleinput();
        updateBackground();

        game.GetGameBird().update(dt);
        cam.position.y= game.GetGameBird().getPosition().y +80;

        for (Branch branch : game.GetGameBranches()){
            if(cam.position.y - (cam.viewportHeight/2) > branch.getPosRightBranch().y + branch.getRightBranch().getHeight()){
                branch.reposition(branch.getPosRightBranch().y + ((Branch.B_HEIGHT + BRANCH_SPACING) * BRANCH_COUNT));
            }
        }

        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //   sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);

        sb.draw(background, backPos1.x, backPos1.y);
        sb.draw(background, backPos2.x, backPos2.y);

        sb.draw(game.GetGameBird().getBirdTexture(), game.GetGameBird().getPosition().x, game.GetGameBird().getPosition().y);

        for (Branch branch : game.GetGameBranches()) {
            sb.draw(branch.getLeftBranch(), branch.getPosLeftBranch().x, branch.getPosLeftBranch().y);
            sb.draw(branch.getRightBranch(), branch.getPosRightBranch().x, branch.getPosRightBranch().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {

    }

    public void updateBackground(){

        if(cam.position.y - (cam.viewportHeight/2) > backPos1.y + background.getHeight())
            backPos1.add(0, background.getHeight()*2);

        if(cam.position.y - (cam.viewportHeight/2) > backPos2.y + background.getHeight())
            backPos2.add(0, background.getHeight()*2);

    }

}
