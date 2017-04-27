package com.anacris.game.states;

import com.anacris.game.GameBird;
import com.anacris.game.sprites.Bird;
import com.anacris.game.sprites.Branch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by cristiana on 24-04-2017.
 */

public class PlayState extends State {

    private static final int BRANCH_SPACING = 125;
    private static final int BRANCH_COUNT = 10;

    private Bird bird;
    private Texture background;
    private Vector2 backPos1, backPos2;


    private Array<Branch> branches;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false,GameBird.WIDTH/2, GameBird.HEIGHT/2);
        background = new Texture("stars.png");

        backPos1 = new Vector2(0,cam.position.y- (cam.viewportHeight));
        backPos2 = new Vector2(0, cam.position.y- (cam.viewportHeight)+ background.getHeight());

        branches = new Array<Branch>();

        for (int i=1; i<BRANCH_COUNT; i++){
            branches.add(new Branch(i* (BRANCH_SPACING + Branch.B_HEIGHT)));
        }
    }

    @Override
    public void handleinput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleinput();
        updateBackground();

        bird.update(dt);

       cam.position.y= bird.getPosition().y +80;


        for (Branch branch : branches){
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

        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);

        for (Branch branch : branches) {
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
