package GameLogic.gameobjects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Class Water:
 * <br>
 * contains methods and values for Water's type objects
 */
public class Water extends GameLogic.gameobjects.Obstacle {
    private Rectangle waterBounds;
    protected int waterIncrement;


    /**
     * Class Constructor Water
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param w     water's texture width
     * @param h     water's texture height
     */
    public Water(int x, int y, int w, int h) {
        super(x, y);
        width = w;
        height = h;
        waterBounds = new Rectangle(x, y, width, height);
    }

    /**
     * Change Water's position to this new coordinates
     * @param x     x - coordinate
     * @param y     y - coordinate
     */
    public void setWaterBoundsPosition(float x, float y){
        waterBounds.setPosition(x,y);
    }

    /**
     * Return a Rectangle that bounds the Water Texture
     * @return a rectangle that surround the water
     */
    public Rectangle getWaterBounds() {
        return waterBounds;
    }

    /**
     * Return a value to make water rise up faster/slower
     * @return waterIncrement
     */
    public int getWaterIncrement() {
        return waterIncrement;
    }

    /**
     * Change water's increment to this new increment
     * @param inc   new increment
     */
    public void setWaterIncrement(int inc) {
        waterIncrement = inc;
    }
}
