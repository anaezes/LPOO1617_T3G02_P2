package GameLogic;

import com.badlogic.gdx.graphics.Texture;


public class Water extends Obstacle {

    private Texture waterTexture;

    public Water(int x, int y)
    {
        super(x, y);
        waterTexture = new Texture("water.png");
    }

    public Texture getWaterTexture() {

        return waterTexture;
    }


}
