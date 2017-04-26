package com.anacris.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by cristiana on 26-04-2017.
 */

public class Branch {
    public static final int B_HEIGHT = 12;           /////Depende da imagem

    private static final int X_FLUCTUATION = 50;
    private static final int GAP_BRANCH = 75;
    private static final int LOWEST_OPENING = 30;

    private Texture leftBranch, rightBranch;
    private Vector2 posLeftBranch;
    private Vector2 posRightBranch;
    private Random rand;


    public Branch(float y){
        leftBranch = new Texture("leftBranch.png");
        rightBranch = new Texture("rightBranch.png");
        rand = new Random();
        posLeftBranch = new Vector2(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y);
        posRightBranch = new Vector2(posLeftBranch.x - GAP_BRANCH - rightBranch.getWidth() ,y);

    }

    public Texture getLeftBranch() {
        return leftBranch;
    }

    public Texture getRightBranch() {
        return rightBranch;
    }

    public Vector2 getPosLeftBranch() {
        return posLeftBranch;
    }

    public Vector2 getPosRightBranch() {
        return posRightBranch;
    }

    public void reposition(float y){
        posLeftBranch.set(rand.nextInt(X_FLUCTUATION) + GAP_BRANCH + LOWEST_OPENING, y);
        posRightBranch.set(posLeftBranch.x - GAP_BRANCH - rightBranch.getWidth() ,y);
    }
}
