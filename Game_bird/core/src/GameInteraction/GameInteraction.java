package GameInteraction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


public class GameInteraction implements Interaction {

    @Override
    public void setInputProcessor(InputProcessor input) {
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void setCatchBackKey(boolean value) {
        Gdx.input.setCatchBackKey(value);
    }

    @Override
    public boolean screenIsTouched() {
        return Gdx.input.justTouched();
    }

    @Override
    public boolean backKeyIsPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.BACK);
    }

    @Override
    public float getAccelerometerX() {
        return Gdx.input.getAccelerometerX();
    }

    @Override
    public void vibrate(int value) {
        Gdx.input.vibrate(value);
    }
}
