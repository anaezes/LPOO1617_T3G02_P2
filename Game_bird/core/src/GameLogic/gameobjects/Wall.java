package GameLogic.gameobjects;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ana on 27-05-2017.
 */

public class Wall extends GameLogic.gameobjects.GameObject {

    Texture texture;

    /**
     * Class Constructor Game Object
     * <br> Create an Object with the following coordinates:
     *
     * @param x x - coordinate
     * @param y y - coordinate
     */
    public Wall(int x, int y) {
        super(x, y);
    }

    public void setTexture(Texture tex) {
        texture = tex;
    }

    public Texture getTexture() {
        return texture;
    }
}
