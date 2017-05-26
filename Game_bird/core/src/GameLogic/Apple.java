package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Apple extends Award {

    private Texture apple;
    private Circle appleBounds;

    public Apple(int x, int y) {
        super(x, y);
        apple = new Texture("apple.png");
        appleBounds = new Circle(x+apple.getWidth()/2, y+apple.getHeight()/2, apple.getWidth()/2);
    }

    public Texture getAppleTexture() {
        return apple;
    }

    public Circle getAppleBounds() {
        return appleBounds;
    }

}
