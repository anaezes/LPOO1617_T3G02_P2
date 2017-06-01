package GameLogic.gameobjects;

import com.badlogic.gdx.math.Circle;

/**
 * Class Star:
 * <br>
 * contains methods and values for Star's type objects
 */
public class Star extends GameLogic.gameobjects.Award {

    private Circle starBounds;

    /**
     * Class Constructor Star
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param w     star's texture width
     * @param h     star's texture height
     */
    public Star(int x, int y, int w, int h) {
        super(x, y);
        width = w;
        height = h;
        starBounds = new Circle(x+width/2, y+height/2, width/2);
    }

    /**
     * Return a circle that bounds the star
     * @return      - circle to surround the star
     */
    public Circle getStarBounds() {
        return starBounds;
    }
}
