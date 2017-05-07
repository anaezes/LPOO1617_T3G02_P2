package GameLogic;
import com.badlogic.gdx.utils.Array;


public class GameMain {
    private int eatenApples;
    private Bird bird;
    private Array<Branch> branches;
    private Water water;
    private EnumGameLevel level;
    private Apple apple;
    private static GameMain instance = null;

    public GameMain() {
        level = EnumGameLevel.LevelOne;
        eatenApples = 0;
    }

    public static GameMain GetInstance() {
        if(instance == null) {
            instance = new GameMain();
        }
        return instance;
    }

    public void createBird(int width) {
        bird = new Bird(100, width ,100);
    }

    public void createWater() {
        water = new Water(0, 0);
    }

    public void createApple(){
        apple = new Apple(50, 900);
    }

    public void createBranchs(int numOfBranchs, int branchSpacing) {
        branches = new Array<Branch>();

        for (int i=1; i<numOfBranchs; i++){
            branches.add(new Branch(0,i* (branchSpacing + Branch.B_HEIGHT)));
        }
    }

    public EnumGameLevel GetCurrentGameLevel() {
        return level;
    }

    public void SetGameLevel(EnumGameLevel level) {
        this.level = level;
    }

    public Bird getGameBird() {
        return bird;
    }

    public Apple getApple() {
        return apple;
    }

    public Water GetWater() {
        return water;
    }

    public Array<Branch> GetGameBranches() {
        return branches;
    }

    public boolean checkCollisions() {

        for (int i=1; i < branches.size; i++){
            if(bird.getBounds().overlaps(branches.get(i).getBoundsLeftBranch()) ||
                    bird.getBounds().overlaps(branches.get(i).getBoundsRightBranch()))
            return true;
        }

        if(bird.getBounds().overlaps(water.getWaterBounds())){
            return true;
        }

    return false;
    }

    public int getEatenApples() {
        return eatenApples;
    }

    public void setEatenApples(int eatenApples) {
        this.eatenApples = eatenApples;
    }
}
