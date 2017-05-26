package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Class BranchLevelTwo (a Branch is an Obstacle :
 * <br>
 * contains methods and values for Branch's type objects
 */
public class BranchLevelTwo extends Branch {
    /**
     * Class Constructor BranchLevelTwo
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create a branch with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public BranchLevelTwo(int x, int y) {
        super(x, y);

        rightBranch = new Texture("rightBranchWinter.png");
        leftBranch = new Texture("leftBranchWinter.png");
        posLeftBranch = new Vector3(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch = new Vector3(posLeftBranch.x - GAP_BRANCH - LOWEST_OPENING, y+rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, 0);
        boundsLeftBranch = new Rectangle(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4, leftBranch.getWidth(), leftBranch.getHeight()/4);
        boundsRightBranch = new Rectangle(posRightBranch.x, posRightBranch.y+3*leftBranch.getHeight()/4, rightBranch.getWidth(), rightBranch.getHeight()/4);
    }
}
