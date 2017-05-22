package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Star extends Award {

    private Texture star;
    private Circle starBounds;

    public Star(int x, int y) {
        super(x, y);
        star = new Texture("star.png");
        starBounds = new Circle(x+star.getWidth()/2, y+star.getHeight()/2, star.getWidth()/2);
    }

    public Texture getStarTexture() {
        return star;
    }

    public Circle getStarBounds() {
        return starBounds;
    }
}
