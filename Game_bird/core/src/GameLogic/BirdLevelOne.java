package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

/**
 * Class BirdLevelOne:
 * <br>
 * contains methods and values for Bird's type objects to Level One
 */

public class BirdLevelOne extends Bird {
    /**
     * Class Constructor BirdLevelOne
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create a bird with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public BirdLevelOne(int x, int y) {
        super(x, y);
        birdTexture = new Texture("birdAnimation.png");
        weight = 0;
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        bounds = new Circle(x+birdAnimation.getFrame().getRegionWidth()/2, y+birdAnimation.getFrame().getRegionHeight()/2, birdAnimation.getFrame().getRegionHeight()/2);
    }
}
