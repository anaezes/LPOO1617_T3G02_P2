package GameLogic;


/**
 * Class GameObject -
 * <br> <br>
 * contains methods and values for element's coordinates
 */
public class GameObject {
    private int posX;
    private int posY;

    /**
     * Class Constructor Game Object
     * <br> Create an Object with the following coordinates:
     * @param x     x - coordinate
     * @param y     y - coordinate
     */
    public  GameObject (int x, int y){
        posX = x;
        posY = y;
    }

    /**
     * Return x coordinate
     * @return     x - coordinate
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Change x coordinate to this new coordinate:
     * @param posX       x - coordinate
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Return y coordinate
     * @return     y - coordinate
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Change y coordinate to this new coordinate:
     * @param posY       y - coordinate
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
