package GameLogic;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by cristiana on 30-04-2017.
 */

public class Water extends Obstacle {

    private Texture waterTexture;

    public Water(int x, int y)
    {
        super(x, y);
        waterTexture = new Texture("water_test.png");
    }

    public Texture getWaterTexture() {
        return waterTexture;
    }
}
