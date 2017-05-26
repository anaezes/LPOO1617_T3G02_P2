package GameLogic;


/**
 * Class WaterLevelTwo:
 * <br>
 * contains methods and values for Water's type objects
 */
public class WaterLevelTwo extends Water {
    /**
     * Class Constructor WaterLevelTwo
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create water with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public WaterLevelTwo(int x, int y) {
        super(x, y);
        waterIncrement = 2;
    }
}
