package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by cristiana on 30-04-2017.
 */

public class Branch extends Obstacle {

    public static final int B_HEIGHT = 12;           /////Depende da imagem

    private static final int X_FLUCTUATION = 50;
    private static final int GAP_BRANCH = 110;
    private static final int LOWEST_OPENING = 30;

    private Texture leftBranch, rightBranch;
    private Vector3 posLeftBranch;
    private Vector3 posRightBranch;
    private Random rand;


    public Branch(int x, int y) {
        super(x, y);

        leftBranch = new Texture("leftBranch.png");
        rightBranch = new Texture("rightBranch.png");
        rand = new Random();
        posLeftBranch = new Vector3(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch = new Vector3(posLeftBranch.x - GAP_BRANCH - rightBranch.getWidth() ,y, 0);

    }

    public Texture getLeftBranch() {
        return leftBranch;
    }

    public Texture getRightBranch() {
        return rightBranch;
    }

    public Vector3 getPosLeftBranch() {
        return posLeftBranch;
    }

    public Vector3 getPosRightBranch() {
        return posRightBranch;
    }

    public void reposition(float y){
        posLeftBranch.set(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch.set(posLeftBranch.x - GAP_BRANCH - rightBranch.getWidth() ,y, 0);
    }
}
