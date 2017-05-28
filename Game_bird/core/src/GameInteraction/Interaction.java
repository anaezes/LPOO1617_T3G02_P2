package GameInteraction;

import com.badlogic.gdx.InputProcessor;

public interface Interaction {
    void setInputProcessor(InputProcessor input);
    void setCatchBackKey(boolean value);
    boolean screenIsTouched();
    boolean backKeyIsPressed();
    float getAccelerometerX();
    void vibrate(int value);
}
