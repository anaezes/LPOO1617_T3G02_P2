package GameLogic.gameobjects;


import com.badlogic.gdx.graphics.Texture;

import GameLogic.EnumGameLevel;

public class Factory {

    public static Bird createBird(EnumGameLevel level, int x, int y) {
        Bird bird = new Bird(x, y);
        Texture texture;
        switch (level){
            case LevelOne:
                texture = new Texture("birdAnimation.png");
                bird.setTexture(texture);
                bird.setWeight(1);
                break;
            case LevelTwo:
                texture = new Texture("birdAnimationWinter.png");
                bird.setTexture(texture);
                bird.setWeight(5f);
                break;
            case LevelThree:
                texture = new Texture("birdAnimationHot.png");
                bird.setTexture(texture);
                bird.setWeight(10f);
                break;
        }

        return bird;
    }

    public static Branch createBranch(EnumGameLevel level, int x, int y) {
        Branch branch = new Branch(x, y);
        Texture textureRightBranch, textureLeftBranch;
        switch (level){
            case LevelOne:
                textureRightBranch = new Texture("rightBranchNormal.png");
                textureLeftBranch = new Texture("leftBranchNormal.png");
                branch.setTextures(textureRightBranch, textureLeftBranch);
                break;
            case LevelTwo:
                textureRightBranch = new Texture("rightBranchWinter.png");
                textureLeftBranch = new Texture("leftBranchWinter.png");
                branch.setTextures(textureRightBranch, textureLeftBranch);
                break;
            case LevelThree:
                textureRightBranch = new Texture("rightBranchHot.png");
                textureLeftBranch = new Texture("leftBranchHot.png");
                branch.setTextures(textureRightBranch, textureLeftBranch);
                break;
        }

        return branch;
    }

    public static Water createWater(EnumGameLevel level, int x, int y) {
        Water water = new Water(x, y);
        switch (level){
            case LevelOne:
                water.setWaterIncrement(2);
                break;
            case LevelTwo:
                water.setWaterIncrement(3);
                break;
            case LevelThree:
                water.setWaterIncrement(4);
                break;
        }
        return water;
    }

    public static Wall createLeftWall(EnumGameLevel level) {
        Wall wall = new Wall(0, 0);
        switch (level) {
            case LevelOne:
                wall.setTexture(new Texture("wallLeftNormal.png"));
                break;

            case LevelTwo:
                wall.setTexture(new Texture("wallLeftWinter.png"));
                break;
            case LevelThree:
                wall.setTexture(new Texture("wallLeftHot.png"));
                break;
        }

        return wall;
    }

    public static Wall createRightWall(EnumGameLevel level) {
        Wall wall = new Wall(0, 0);

        switch (level) {
            case LevelOne:
                wall.setTexture(new Texture("wallRightNormal.png"));
                break;
            case LevelTwo:
                wall.setTexture(new Texture("wallRightWinter.png"));
                break;
            case LevelThree:
                wall.setTexture(new Texture("wallRightHot.png"));
                break;
        }
        return wall;
    }
}
