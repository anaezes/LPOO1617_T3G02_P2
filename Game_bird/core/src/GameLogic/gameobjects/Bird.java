package GameLogic.gameobjects;

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
    protected Circle bounds;

    public float birdPosMinX;
    public float birdPosMaxX;
    public float birdPosMinY;

    /**
     *  Class Constructor Bird
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param textureW      bird's texture width
     * @param textureH      bird's texture height
     * <br>
     * Create a bird with x and y coordinates, attach it to a texture and a Circle (to detect collisions)
     */
    public Bird(int x, int y, int textureW, int textureH) {
        super(x, y);

        height = textureH;
        width = textureW;

        bounds = new Circle(getPosX()+width/2, getPosY()+height/2, height/2-3);

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);

        birdPosMinX = 0;
        birdPosMaxX = 400;
        birdPosMinY = 0;
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
    public void update(float dt, float ax) {

        if (position.y > 15)
            velocity.add(GRAVITY_X, GRAVITY_Y-weight, 0);

        velocity.scl(dt);

        if(position.x + (-ax) < birdPosMinX ||
                (position.x + width + (-ax) > birdPosMaxX))
            position.add(0, velocity.y, 0);
        else
            position.add(-ax/2, velocity.y, 0);

        if (position.y <= birdPosMinY)
            position.y = birdPosMinY;

        velocity.scl(1 / dt);

        bounds.setPosition(position.x+width/2, position.y+height/2);
    }

    /**
     * Return bird's position
     * @return position
     */
    public Vector3 getPosition() {
        return position;
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
     * @param minX      minimum x position
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
}
