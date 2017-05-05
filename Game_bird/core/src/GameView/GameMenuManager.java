package GameView;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by cristiana on 30-04-2017.
 */

public class GameMenuManager {

    private static GameMenuManager instance = null;

    private Stack<State> states;

    public GameMenuManager(){
        states = new Stack<State>();
    }

    public static GameMenuManager GetInstance() {
        if (instance == null) {
            instance = new GameMenuManager();
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
