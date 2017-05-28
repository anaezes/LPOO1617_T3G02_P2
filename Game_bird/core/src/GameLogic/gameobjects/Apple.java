package GameLogic.gameobjects;

import com.badlogic.gdx.math.Circle;

/**
 * Class Apple (an apple is an Award):
 * <br>
 * contains methods and values for Apple's type objects
 */
public class Apple extends Award {

    private int width;
    private int height;
    private Circle appleBounds;

    /**
     * Class Constructor Apple
     * @param x     x-coordinate
     * @param y		y-coordinate
     *<br>
     * Create an apple with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */

    public Apple(int x, int y, int w, int h) {
        super(x, y);
        width = w;
        height = h;
        appleBounds = new Circle(x+width/2, y+height/2, width/2);
    }

    /**
     * Return bounds of an apple
     * @return a Circle that bounds an apple
     */
    public Circle getAppleBounds() {
        return appleBounds;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}