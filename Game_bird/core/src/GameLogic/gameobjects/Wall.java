package GameLogic.gameobjects;

public class Wall extends GameLogic.gameobjects.GameObject {

    /**
     * Class Constructor Game Object
     * <br> Create an Object with the following coordinates:
     * @param x     x - coordinate
     * @param y     y - coordinate
     * @param w     wall's texture width
     * @param h     wall's texture height
     */
    public Wall(int x, int y, int w, int h) {
        super(x, y);
        width = w;
        height = h;
    }

}
