package GameLogic.gameobjects;


import GameLogic.EnumGameLevel;

public class Factory {

    public static Bird createBird(EnumGameLevel level, int x, int y, int textureWidth, int textureHeight) {
        Bird bird = new Bird(x, y, textureWidth, textureHeight);
        switch (level){
            case LevelOne:
                bird.setWeight(1);
                break;
            case LevelTwo:
                bird.setWeight(5f);
                break;
            case LevelThree:
                bird.setWeight(10f);
                break;
        }

        return bird;
    }

    public static Water createWater(EnumGameLevel level, int x, int y, int w, int h) {
        Water water = new Water(x, y, w, h);
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
}
