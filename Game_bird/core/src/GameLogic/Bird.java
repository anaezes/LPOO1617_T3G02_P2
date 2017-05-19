package GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;


public class Bird extends GameObject {

    private double weight;
    private static final int GRAVITY_X = -5;
    private static final int GRAVITY_Y = -7;
    private Vector3 position;
    private Vector3 velocity;
    private Texture birdTexture;
    private Animation birdAnimation;
    private Circle bounds;

    private float birdPosMinX;
    private float birdPosMaxX;
    private float birdPosMinY;

    public Bird(int x, int y, double w) {
        super(x, y);
        weight = w;

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);

        birdTexture = new Texture("birdanimation2.png");
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        bounds = new Circle(x+birdAnimation.getFrame().getRegionWidth()/2, y+birdAnimation.getFrame().getRegionHeight()/2, birdAnimation.getFrame().getRegionHeight()/4);
    }

    public double getWeight() {
        return weight;
    }

    public void update(float dt) {

        birdAnimation.update(dt);

        if (position.y > 15)
            velocity.add(GRAVITY_X, GRAVITY_Y, 0);

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

    public Circle getBounds() {
        return bounds;
    }
}
