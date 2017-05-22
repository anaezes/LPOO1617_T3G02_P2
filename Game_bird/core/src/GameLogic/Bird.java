package GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;


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

    public Bird(int x, int y) {
        super(x, y);

        birdStars = new Texture("starsbird.png");
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
    }

    public float getWeight() {
        return weight;
    }

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

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBirdTexture() {
        return birdAnimation.getFrame();
    }

    public Texture getBirdStarsTexture() {
        return birdStars;
    }

    public void jump(){
        velocity.y = 300;
        velocity.x = 300;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setValidPositionsX(float minX, float maxX, float minY) {
        birdPosMinX = minX;
        birdPosMaxX = maxX;
        birdPosMinY = minY;
    }

    public Circle getBounds() {
        return bounds;
    }
}
