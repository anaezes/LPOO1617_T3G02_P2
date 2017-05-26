package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;


public class Branch extends Obstacle {

    public static final int B_HEIGHT = 150;           /////Depende da imagem
    protected static final int X_FLUCTUATION = 60;
    protected static final int GAP_BRANCH = 110;
    protected static final int LOWEST_OPENING = 20;

    protected Texture rightBranch, leftBranch;
    protected Rectangle boundsRightBranch, boundsLeftBranch;
    protected Vector3 posLeftBranch;
    protected Vector3 posRightBranch;
    protected Random rand;

    public Branch(int x, int y) {
        super(x, y);
        rand = new Random();
    }

    public Texture getLeftBranch() {
        return rightBranch;
    }

    public Texture getRightBranch() {
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
        posRightBranch.set(posLeftBranch.x - GAP_BRANCH - leftBranch.getWidth(), y+rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, 0);

        boundsLeftBranch.setPosition(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4);
        boundsRightBranch.setPosition(posRightBranch.x, posRightBranch.y+3*rightBranch.getHeight()/4);
    }

    public Rectangle getBoundsRightBranch() {
        return boundsRightBranch;
    }
    public Rectangle getBoundsLeftBranch() {
        return boundsLeftBranch;
    }
}
