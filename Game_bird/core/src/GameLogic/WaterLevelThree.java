package GameLogic;

/**
 * Class WaterLevelThree:
 * <br>
 * contains methods and values for Water's type objects
 */
public class WaterLevelThree extends Water {
    /**
     * Class Constructor WaterLevelThree
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create water with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public WaterLevelThree(int x, int y) {
        super(x, y);
        waterIncrement = 4;
    }
}
