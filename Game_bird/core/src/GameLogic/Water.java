package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Water extends Obstacle {
    private Rectangle waterBounds;
    private Texture waterTexture;
    protected int waterIncrement;

    public Water(int x, int y) {
        super(x, y);
        waterTexture = new Texture("water.png");
        waterBounds = new Rectangle(x, y, waterTexture.getWidth(), waterTexture.getHeight());
    }

    public Texture getWaterTexture() {
        return waterTexture;
    }

    public void setWaterBoundsPosition(float x, float y){
        waterBounds.setPosition(x,y);
    }

    public Rectangle getWaterBounds() {
        return waterBounds;
    }

    public int getWaterIncrement() {
        return waterIncrement;
    }
}
