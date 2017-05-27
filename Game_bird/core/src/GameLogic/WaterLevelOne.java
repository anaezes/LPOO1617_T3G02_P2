package GameLogic;

/**
 * Class WaterLevelOne:
 * <br>
 * contains methods and values for Water's type objects
 */

public class WaterLevelOne extends Water {
    /**
     * Class Constructor WaterLevelOne
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create water with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public WaterLevelOne(int x, int y) {
        super(x, y);
        waterIncrement = 2;
    }
}
