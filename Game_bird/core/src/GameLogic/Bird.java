package GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Bird extends GameObject {

    private double weight;
    private static final int GRAVITY_X = -5;
    private static final int GRAVITY_Y = -7;
    private Vector3 position;
    private Vector3 velocity;
    private Texture birdTexture;
    private Rectangle bounds;

    private float birdPosMinX;
    private float birdPosMaxX;
    private float birdPosMinY;

    public Bird(int x, int y, double w) {
        super(x, y);
        weight = w;
        birdTexture = new Texture("bird.png");
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);

        bounds = new Rectangle(x, y, birdTexture.getWidth(), birdTexture.getHeight());

    }

    public double getWeight() {
        return weight;
    }

    public void update(float dt) {
        if (position.y > 15)
            velocity.add(GRAVITY_X, GRAVITY_Y, 0);

        velocity.scl(dt);

        if(position.x + (-Gdx.input.getAccelerometerX()) < birdPosMinX ||
                (position.x + birdTexture.getWidth() + (-Gdx.input.getAccelerometerX()) > birdPosMaxX))
            position.add(0, velocity.y, 0);
        else
            position.add(-Gdx.input.getAccelerometerX(), velocity.y, 0);

        if (position.y <= birdPosMinY)
            position.y = birdPosMinY;

        velocity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBirdTexture() {
        return birdTexture;
    }

    public void jump(){
        velocity.y = 300;
        velocity.x = 300;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setValidPositionsX(float minX, float maxX, float minY) {
        birdPosMinX = minX;
        birdPosMaxX = maxX;
        birdPosMinY = minY;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
