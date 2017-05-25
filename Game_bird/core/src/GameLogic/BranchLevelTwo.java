package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class BranchLevelTwo extends Branch {
    public BranchLevelTwo(int x, int y) {
        super(x, y);

        rightBranch = new Texture("rightBranchWinter.png");
        leftBranch = new Texture("leftBranchWinter.png");
        posLeftBranch = new Vector3(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y, 0);
        posRightBranch = new Vector3(posLeftBranch.x - GAP_BRANCH - leftBranch.getWidth(), y+rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, 0);
        boundsLeftBranch = new Rectangle(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4, leftBranch.getWidth(), leftBranch.getHeight()/4);
        boundsRightBranch = new Rectangle(posRightBranch.x + rightBranch.getWidth()/3, posRightBranch.y+3*leftBranch.getHeight()/4, rightBranch.getWidth(), rightBranch.getHeight()/4);

        posRectangle1 = new Vector3(posLeftBranch.x, posLeftBranch.y+3*leftBranch.getHeight()/4,0);
        posRectangle2 = new Vector3(posRightBranch.x-rightBranch.getWidth()/3, posRightBranch.y+3*leftBranch.getHeight()/4,0);
    }
}
