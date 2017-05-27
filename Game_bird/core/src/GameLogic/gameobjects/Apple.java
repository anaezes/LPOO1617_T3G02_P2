package GameLogic.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 * Class Apple (an apple is an Award):
 * <br>
 * contains methods and values for Apple's type objects
 */
public class Apple extends Award {

    private Texture apple;
    private Circle appleBounds;

    /**
     * Class Constructor Apple
     * @param x     x-coordinate
     * @param y		y-coordinate
     *<br>
     * Create an apple with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */

    public Apple(int x, int y) {
        super(x, y);
        apple = new Texture("apple.png");
        appleBounds = new Circle(x+apple.getWidth()/2, y+apple.getHeight()/2, apple.getWidth()/2);
    }

    /**
     * Return a texture attached to an apple
     * @return  a Texture that represents an Apple
     */
    public Texture getAppleTexture() {
        return apple;
    }

    /**
     * Return bounds of an apple
     * @return a Circle that bounds an apple
     */
    public Circle getAppleBounds() {
        return appleBounds;
    }

}
