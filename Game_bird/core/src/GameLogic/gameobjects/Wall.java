package GameLogic.gameobjects;

public class Wall extends GameLogic.gameobjects.GameObject {

    private int width;
    private int height;
    /**
     * Class Constructor Game Object
     * <br> Create an Object with the following coordinates:
     *
     * @param x x - coordinate
     * @param y y - coordinate
     */
    public Wall(int x, int y, int w, int h) {
        super(x, y);
        width = w;
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
