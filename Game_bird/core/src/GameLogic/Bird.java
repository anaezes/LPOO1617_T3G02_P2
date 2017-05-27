package GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

/**
 * Class Bird:
 * <br>
 * contains methods and values for Bird's type objects
 */

public class Bird extends GameObject {

    protected float weight;
    protected static final int GRAVITY_X = -5;
    protected static final int GRAVITY_Y = -7;
    protected Vector3 position;
    protected Vector3 velocity;
    protected Texture birdTexture;
    protected Texture birdStars;
    protected Animation birdAnimation;
    protected Circle bounds;

    protected float birdPosMinX;
    protected float birdPosMaxX;
    protected float birdPosMinY;

    private Sound s;
    /**
     * Class Constructor Bird
     * @param x     x-coordinate
     * @param y     y-coordinate
     * <br>
     * Create a bird with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public Bird(int x, int y) {
        super(x, y);

        birdStars = new Texture("starsbird.png");
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
    }

    /**
     * Return bird's weight
     * @return weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Update bird's movement (position and velocity)
     * @param dt    delta time
     *              <br> used  to calculate velocity and bird's position
     */
    public void update(float dt) {
        birdAnimation.update(dt);

        System.out.println("PESO" + weight);
        if (position.y > 15)
            velocity.add(GRAVITY_X, GRAVITY_Y-weight, 0);

        velocity.scl(dt);

        if(position.x + (-Gdx.input.getAccelerometerX()) < birdPosMinX ||
                (position.x + birdAnimation.getFrame().getRegionWidth() + (-Gdx.input.getAccelerometerX()) > birdPosMaxX))
            position.add(0, velocity.y, 0);
        else
            position.add(-Gdx.input.getAccelerometerX()/2, velocity.y, 0);

        if (position.y <= birdPosMinY)
            position.y = birdPosMinY;

        velocity.scl(1 / dt);

        bounds.setPosition(position.x+birdAnimation.getFrame().getRegionWidth()/2, position.y+birdAnimation.getFrame().getRegionHeight()/2);


    }

    /**
     * Return bird's position
     * @return position
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Return bird's frame used in its animation
     * @return bird's animation
     */
    public TextureRegion getBirdTexture() {
        return birdAnimation.getFrame();
    }

    /**
     * Return a special bird's texture (when it collides with a branch)
     * @return bird's special texture
     */
    public Texture getBirdStarsTexture() {
        return birdStars;
    }

    /**
     * Update x and y velocity when bird jumps
     */
    public void jump(){
        velocity.y = 300;
        velocity.x = 300;
    }

    /**
     * Change bird's weight to the new weight
     * @param weight    - new weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Set valid position to consider in bird's movement
     * @param minX      minimum x postion
     * @param maxX      maximum x position
     * @param minY      minimum y position
     */
    public void setValidPositionsX(float minX, float maxX, float minY) {
        birdPosMinX = minX;
        birdPosMaxX = maxX;
        birdPosMinY = minY;
    }

    /**
     * Return a circle that bounds the bird
     * @return      - circle to surround the bird
     */
    public Circle getBounds() {
        return bounds;
    }

    public void setTexture(Texture texture) {
        this.birdTexture = texture;
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        bounds = new Circle(getPosX()+birdAnimation.getFrame().getRegionWidth()/2, getPosY()+birdAnimation.getFrame().getRegionHeight()/2,
                birdAnimation.getFrame().getRegionHeight()/2);
    }


}
