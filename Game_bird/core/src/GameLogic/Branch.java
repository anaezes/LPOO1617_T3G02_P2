package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by cristiana on 30-04-2017.
 */

public class Branch extends Obstacle {

    public static final int B_HEIGHT = 150;           /////Depende da imagem

    private static final int X_FLUCTUATION = 60;
    private static final int GAP_BRANCH = 110;
    private static final int LOWEST_OPENING = 30;

    private Texture rightBranch, leftBranch;
    private Vector3 posLeftBranch;
    private Vector3 posRightBranch;
    private Random rand;


    public Branch(int x, int y) {
        super(x, y);

        rightBranch = new Texture("rightBranch2.png");
        leftBranch = new Texture("leftBranch2.png");
        rand = new Random();
        posLeftBranch = new Vector3(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch = new Vector3(posLeftBranch.x - GAP_BRANCH - leftBranch.getWidth(), y+rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, 0);

    }

    public Texture getRightBranch() {
        return rightBranch;
    }

    public Texture getLeftBranch() {
        return leftBranch;
    }

    public Vector3 getPosLeftBranch() {
        return posLeftBranch;
    }

    public Vector3 getPosRightBranch() {
        return posRightBranch;
    }

    public void reposition(float y){
        posLeftBranch.set(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch.set(posLeftBranch.x - GAP_BRANCH - leftBranch.getWidth() ,y, 0);
    }
}
