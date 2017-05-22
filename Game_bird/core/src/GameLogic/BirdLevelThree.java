package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;


public class BirdLevelThree extends Bird {
    public BirdLevelThree(int x, int y) {
        super(x, y);
        birdTexture = new Texture("birdAnimationHot.png");
        weight = 11f;
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        bounds = new Circle(x+birdAnimation.getFrame().getRegionWidth()/2, y+birdAnimation.getFrame().getRegionHeight()/2, birdAnimation.getFrame().getRegionHeight()/4);
    }
}
