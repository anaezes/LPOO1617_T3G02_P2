package GameInteraction;

import com.badlogic.gdx.InputProcessor;

/**
 * Interface Interaction
 * <br> Provide methods responsible for interaction of application with user
 */
public interface Interaction {
    void setInputProcessor(InputProcessor input);
    void setCatchBackKey(boolean value);
    boolean screenIsTouched();
    boolean backKeyIsPressed();
    float getAccelerometerX();
    void vibrate(int value);
}
