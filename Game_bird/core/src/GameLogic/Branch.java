package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;


public class Branch extends Obstacle {

    public static final int B_HEIGHT = 150;           /////Depende da imagem

    private static final int X_FLUCTUATION = 60;
    private static final int GAP_BRANCH = 110;
    private static final int LOWEST_OPENING = 30;

    private Texture rightBranch, leftBranch, rectangle1, rectangle2;
    private Rectangle boundsRightBranch, boundsLeftBranch;
    private Vector3 posLeftBranch;
    private Vector3 posRightBranch;
    private Random rand;

    private Vector3 posRectangle1, posRectangle2;

    public Branch(int x, int y) {
        super(x, y);

        rightBranch = new Texture("rightBranch2.png");
        leftBranch = new Texture("leftBranch2.png");
        //rectangle1 = new Texture("rectangle.png");
        //rectangle2 = new Texture("rectangle.png");

        rand = new Random();
        posLeftBranch = new Vector3(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch = new Vector3(posLeftBranch.x - GAP_BRANCH - leftBranch.getWidth(), y+rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, 0);

        //debug
        //posRectangle1 = new Vector3(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4,0);
        //posRectangle2 = new Vector3(posRightBranch.x-rightBranch.getWidth()/3, posRightBranch.y+3*leftBranch.getHeight()/4,0);

        boundsLeftBranch = new Rectangle(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4, leftBranch.getWidth(), leftBranch.getHeight()/4);
        boundsRightBranch = new Rectangle(posRightBranch.x + rightBranch.getWidth()/3, posRightBranch.y+3*leftBranch.getHeight()/4, rightBranch.getWidth(), rightBranch.getHeight()/4);
    }

    public Texture getLeftBranch() {
        return rightBranch;
    }

    public Texture getRightBranch() {
        return leftBranch;
    }

    public Vector3 getPosLeftBranch() {
        return posLeftBranch;
    }

    public Vector3 getPosRightBranch() {
        return posRightBranch;
    }

    public void reposition(float y){
        posLeftBranch.set(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch.set(posLeftBranch.x - GAP_BRANCH - leftBranch.getWidth() ,y, 0);

        boundsLeftBranch.setPosition(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4);
        boundsRightBranch.setPosition(posRightBranch.x-rightBranch.getWidth()/4, posRightBranch.y+3*rightBranch.getHeight()/4);

        //posRectangle1.set(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4, 0);
        //posRectangle2.set(posRightBranch.x-rightBranch.getWidth()/3, posRightBranch.y+3*leftBranch.getHeight()/4, 0);
    }

    public Rectangle getBoundsRightBranch() {
        return boundsRightBranch;
    }
    public Rectangle getBoundsLeftBranch() {
        return boundsLeftBranch;
    }

    public Texture getRectangle1() {
        return rectangle1;
    }

    public Texture getRectangle2() {
        return rectangle2;
    }

    public Vector3 getPosRectangle1() {
        return posRectangle1;
    }

    public Vector3 getPosRectangle2() {
        return posRectangle2;
    }
}
