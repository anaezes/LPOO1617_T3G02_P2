package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;


public class birdLevelThree extends Bird {
    public birdLevelThree(int x, int y) {
        super(x, y);
        birdTexture = new Texture("birdanimation2.png");
        weight = 15f;
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        bounds = new Circle(x+birdAnimation.getFrame().getRegionWidth()/2, y+birdAnimation.getFrame().getRegionHeight()/2, birdAnimation.getFrame().getRegionHeight()/4);
    }
}
