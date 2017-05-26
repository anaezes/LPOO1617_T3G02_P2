package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

/**
 * Class BirdLevelThree:
 * <br>
 * contains methods and values for Bird's type objects to Level Three
 */
public class BirdLevelThree extends Bird {

    /**
     * Class Constructor BirdLevelThree
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create a bird with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public BirdLevelThree(int x, int y) {
        super(x, y);
        birdTexture = new Texture("birdAnimationHot.png");
        weight = 11f;
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        bounds = new Circle(x+birdAnimation.getFrame().getRegionWidth()/2, y+birdAnimation.getFrame().getRegionHeight()/2, birdAnimation.getFrame().getRegionHeight()/2);
    }
}
