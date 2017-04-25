package com.anacris.game.states;

import com.anacris.game.GameBird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

/**
 * Created by cristiana on 23-04-2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playbtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background=new Texture("backg.png");
        playbtn=new Texture("playbtn.png");
    }

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
                gsm.set(new PlayState(gsm));
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
        sb.draw(background, 0,0, GameBird.WIDTH, GameBird.HEIGHT);
        sb.draw(playbtn, 175, 150);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
    }
}
