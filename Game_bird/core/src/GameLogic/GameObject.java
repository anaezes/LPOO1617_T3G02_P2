package GameLogic;


public class GameObject {
    private int posX;
    private int posY;

    public  GameObject (int x, int y){
        posX = x;
        posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
