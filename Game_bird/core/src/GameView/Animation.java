package GameView;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Class Animation
 *  <br>contains methods and values to create an ilusion of bird's movement
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    /**
     * Class Constructor Animation
     * @param region        - bird's animated texture
     * @param frameCount        - counter frame
     * @param cycleTime     - cycleTime
     *
     * <br> Put animation in game's bird texture
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;

        for (int i = 0; i < frameCount; i++)
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));


        this.frameCount =frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;

    }

    /**
     * Method to update bird's frame animation
     * @param dt    - delta time
     */
    public void update(float dt) {
        currentFrameTime += dt;

        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }

        if(frame >= frameCount)
            frame = 0;
    }

    /**
     * Return bird's frame
     * @return      bird's frame
     */
    public TextureRegion getFrame() {
        return frames.get(frame);
    }

}
