package com.anacris.game.states;

import com.anacris.game.GameBird;
import com.anacris.game.sprites.Bird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by cristiana on 24-04-2017.
 */

public class PlayState extends State {
    private Bird bird;
    private Texture background;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        background = new Texture("backg.png");
        cam.setToOrtho(false,GameBird.WIDTH/2, GameBird.HEIGHT/2);
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
        bird.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
       sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
