package GameLogic.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;


/**
 * Class Branch (a Branch is an Obstacle :
 * <br>
 * contains methods and values for Branch's type objects
 */
public class Branch extends Obstacle {

    public static final int B_HEIGHT = 100;          
    protected static final int X_FLUCTUATION = 20;
    protected static final int GAP_BRANCH = 110;
    protected static final int LOWEST_OPENING = 50;

    protected Rectangle boundsRightBranch, boundsLeftBranch;
    protected Vector3 posLeftBranch;
    protected Vector3 posRightBranch;
    protected Random rand;

    private int width;
    private int height;


    /**
     * Class Constructor Branch
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param w     brance's texture width
     * @param h     branche's texture height
     */
    public Branch(int x, int y, int w, int h) {
        super(x, y);
        width = w;
        height = h;
        rand = new Random();

        posLeftBranch = new Vector3(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, getPosY(), 0);
        posRightBranch = new Vector3(posLeftBranch.x - GAP_BRANCH - LOWEST_OPENING, getPosY() + rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, 0);

        boundsLeftBranch = new Rectangle(posLeftBranch.x, posLeftBranch.y+3*height/4, width, height/4);
        boundsRightBranch = new Rectangle(posRightBranch.x, posRightBranch.y+3*height/4, width, height/4);
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

        boundsLeftBranch.setPosition(posLeftBranch.x, posLeftBranch.y+3*height/4);
        boundsRightBranch.setPosition(posRightBranch.x, posRightBranch.y+3*height/4);
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

    /**
     * Return branche's texture width
     * @return      branche's texture width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return branche's texture height
     * @return      branche's texture height
     */
    public int getHeight() {
        return height;
    }
}
