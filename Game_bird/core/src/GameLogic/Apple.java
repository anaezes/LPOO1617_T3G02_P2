package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by cristiana on 30-04-2017.
 */

public class Apple extends Award {

    private Texture apple;
    private Rectangle appleBounds;

    public Apple(int x, int y) {
        super(x, y);
        apple = new Texture("apple.png");
        appleBounds = new Rectangle(x,y,apple.getWidth(), apple.getHeight());
    }

    public Texture getAppleTexture() {
        return apple;
    }

    public Rectangle getAppleBounds() {
        return appleBounds;
    }
}
