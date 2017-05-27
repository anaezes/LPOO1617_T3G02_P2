package GameLogic.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 * Class Star:
 * <br>
 * contains methods and values for Star's type objects
 */
public class Star extends GameLogic.gameobjects.Award {

    private Texture star;
    private Circle starBounds;

    /**
     * Class Constructor Star
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create a bird with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public Star(int x, int y) {
        super(x, y);
        star = new Texture("star.png");
        starBounds = new Circle(x+star.getWidth()/2, y+star.getHeight()/2, star.getWidth()/2);
    }

    /**
     * Return star Texture
     * @return Texture that represents a star
     */
    public Texture getStarTexture() {
        return star;
    }

    /**
     * Return a circle that bounds the star
     * @return      - circle to surround the star
     */
    public Circle getStarBounds() {
        return starBounds;
    }
}
