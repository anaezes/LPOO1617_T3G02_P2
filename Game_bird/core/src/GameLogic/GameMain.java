package GameLogic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by cristiana on 30-04-2017.
 */

public class GameMain {
    private Bird bird;
    private Array<Branch> branches;
    private EnumGameLevel level;

    public GameMain() {
        level = EnumGameLevel.LevelOne;
    }

    public void createBird() {
        bird = new Bird(50,300,100);
    }

    public void createBranchs(int numOfBranchs, int branchSpacing) {
        branches = new Array<Branch>();

        for (int i=1; i<numOfBranchs; i++){
            branches.add(new Branch(0,i* (branchSpacing + Branch.B_HEIGHT)));
        }
    }

    public EnumGameLevel GetCurrentGameLevel() {
        return level;
    }

    public void SetGameLevel(EnumGameLevel level) {
        this.level = level;
    }

    public Bird GetGameBird() {
        return bird;
    }

    public Array<Branch> GetGameBranches() {
        return branches;
    }

}
