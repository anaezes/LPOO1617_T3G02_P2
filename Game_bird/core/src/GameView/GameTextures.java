package GameView;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

import GameLogic.EnumGameLevel;

/**
 * Class to store game's textures
 */
public class GameTextures {
    public Texture birdTexture;
    public Animation birdAnimation;
    public final Texture birdStars = new Texture("starsbird.png");
    public final Texture appleTexture = new Texture("apple.png");
    public final Texture starTexture = new Texture("star.png");
    public final Texture waterTexture = new Texture("water.png");
    public final Texture gameOver = new Texture("gameOver.png");
    public List<Texture> wallTextures;
    public List<Texture> branchTextures;

    /**
     * Create textures according to game's level
     * @param level  level
     */
    public GameTextures(EnumGameLevel level){
        birdTexture = getBirdTexture(level);
        wallTextures = getWallTextures(level);
        branchTextures = getBranchTextures(level);
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
    }

    /**
     * Return game's bird texture according to game's level
     * @param level     - level's game
     * @return
     */
    private Texture getBirdTexture(EnumGameLevel level) {
        Texture texture;
        switch (level){
            case LevelOne:
                texture = new Texture("birdAnimation.png");
                break;
            case LevelTwo:
                texture = new Texture("birdAnimationWinter.png");
                break;
            case LevelThree:
                texture = new Texture("birdAnimationHot.png");
                break;
            default:
                texture = new Texture("birdAnimation.png");
                break;
        }

        return texture;
    }

    /**
     * Return branches's textures according to game's level
     * @param level   game's level
     * @return
     */
    private List<Texture> getBranchTextures(EnumGameLevel level) {
       List<Texture> textures = new ArrayList<Texture>(2);
        Texture textureRightBranch, textureLeftBranch;

        switch (level){
            case LevelOne:
                textureRightBranch = new Texture("rightBranchNormal.png");
                textureLeftBranch = new Texture("leftBranchNormal.png");
                break;
            case LevelTwo:
                textureRightBranch = new Texture("rightBranchWinter.png");
                textureLeftBranch = new Texture("leftBranchWinter.png");
                break;
            case LevelThree:
                textureRightBranch = new Texture("rightBranchHot.png");
                textureLeftBranch = new Texture("leftBranchHot.png");
                break;
            default:
                textureRightBranch = new Texture("rightBranchNormal.png");
                textureLeftBranch = new Texture("leftBranchNormal.png");
                break;
        }
        textures.add(textureLeftBranch);
        textures.add(textureRightBranch);
        return textures;
    }

    /**
     * Return wall's textures according to game's level
     * @param level   game's level
     * @return
     */
    private  List<Texture> getWallTextures(EnumGameLevel level) {
        List<Texture> textures = new ArrayList<Texture>(2);
        Texture textureRightWall, textureLeftWall;

        switch (level){
            case LevelOne:
                textureRightWall = new Texture("wallRightNormal.png");
                textureLeftWall = new Texture("wallLeftNormal.png");
                break;
            case LevelTwo:
                textureRightWall = new Texture("wallRightWinter.png");
                textureLeftWall = new Texture("wallLeftWinter.png");
                break;
            case LevelThree:
                textureRightWall = new Texture("wallRightHot.png");
                textureLeftWall = new Texture("wallLeftHot.png");
                break;
            default:
                textureRightWall = new Texture("wallRightNormal.png");
                textureLeftWall = new Texture("wallLeftNormal.png");
                break;
        }
        textures.add(textureLeftWall);
        textures.add(textureRightWall);
        return textures;
    }
}
