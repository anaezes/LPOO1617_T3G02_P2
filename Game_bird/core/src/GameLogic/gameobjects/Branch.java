package GameLogic.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;


/**
 * Class Branch (a Branch is an Obstacle :
 * <br>
 * contains methods and values for Branch's type objects
 */
public class Branch extends Obstacle {

    public static final int B_HEIGHT = 100;           /////Depende da imagem
    protected static final int X_FLUCTUATION = 20;
    protected static final int GAP_BRANCH = 110;
    protected static final int LOWEST_OPENING = 50;

    protected Texture rightBranch, leftBranch;
    protected Rectangle boundsRightBranch, boundsLeftBranch;
    protected Vector3 posLeftBranch;
    protected Vector3 posRightBranch;
    protected Random rand;

    /**
     * Class Constructor Branch
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create a branch with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public Branch(int x, int y) {
        super(x, y);
        rand = new Random();
    }

    /**
     * Return LeftBranch Texture
     * @return a Texture that represents the left branch
     */
    public Texture getLeftBranch() {
        return rightBranch;
    }

    /**
     * Return RightBranch Texture
     * @return a Texture that represents the right branch
     */
    public Texture getRightBranch() {
        return leftBranch;
    }

    /**
     * Return LeftBranch position
     * @return a vector with x, y, z position
     */
    public Vector3 getPosLeftBranch() {
        return posLeftBranch;
    }

    /**
     * Return RightBranch position
     * @return a vector with x, y, z position
     */
    public Vector3 getPosRightBranch() {
        return posRightBranch;
    }

    /**
     * Calculate next branche's position
     * <br> Updates Rectangle's position (rectangles that bounds the branches)
     * @param y     y - coordinate to set next position
     */
    public void reposition(float y){
        posLeftBranch.set(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch.set(posLeftBranch.x - GAP_BRANCH - LOWEST_OPENING, y+rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, 0);

        boundsLeftBranch.setPosition(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4);
        boundsRightBranch.setPosition(posRightBranch.x, posRightBranch.y+3*rightBranch.getHeight()/4);
    }

    /**
     * Return Rectangle that bounds rightBranch
     * @return a Rectangle
     */
    public Rectangle getBoundsRightBranch() {
        return boundsRightBranch;
    }

    /**
     * Return Rectangle that bounds leftBranch
     * @return a Rectangle
     */
    public Rectangle getBoundsLeftBranch() {
        return boundsLeftBranch;
    }

    public void setTextures(Texture textureRightBranch, Texture textureLeftBranch) {
        rightBranch = textureRightBranch;
        leftBranch = textureLeftBranch;

        posLeftBranch = new Vector3(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, getPosY(), 0);
        posRightBranch = new Vector3(posLeftBranch.x - GAP_BRANCH - LOWEST_OPENING, getPosY() + rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, 0);

        boundsLeftBranch = new Rectangle(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4, leftBranch.getWidth(), leftBranch.getHeight()/4);
        boundsRightBranch = new Rectangle(posRightBranch.x, posRightBranch.y+3*leftBranch.getHeight()/4, rightBranch.getWidth(), rightBranch.getHeight()/4);
    }
}
