package GameView;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by cristiana on 30-04-2017.
 */

public class GameStateManager {

    private static GameStateManager instance = null;

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public static GameStateManager GetInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    public void push (State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set (State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
