package GameInteraction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Class GameInteraction
 * <br> Implements Interface Interaction
 * <br> Manage interaction of application with user
 */
public class GameInteraction implements Interaction {


    public void setInputProcessor(InputProcessor input) {
        Gdx.input.setInputProcessor(input);
    }


    public void setCatchBackKey(boolean value) {
        Gdx.input.setCatchBackKey(value);
    }


    public boolean screenIsTouched() {
        return Gdx.input.justTouched();
    }


    public boolean backKeyIsPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.BACK);
    }


    public float getAccelerometerX() {
        return Gdx.input.getAccelerometerX();
    }


    public void vibrate(int value) {
        Gdx.input.vibrate(value);
    }
}
