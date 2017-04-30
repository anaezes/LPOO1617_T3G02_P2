package GameLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by cristiana on 30-04-2017.
 */

public class Bird extends GameObject {

    private double weight;
    private static final int GRAVITY = -5;
    private Vector3 position;
    private Vector3 velocity;
    private Texture birdTexture;

    public Bird(int x, int y, double w) {
        super(x, y);
        weight = w;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        birdTexture = new Texture("bird.png");
    }

    public double getWeight() {
        return weight;
    }

    public void update(float dt){
        if(position.y > 0)
            velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(0,velocity.y,0);

        if(position.y <=0)
            position.y=0;
        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBirdTexture() {
        return birdTexture;
    }

    public void jump(){
        velocity.y = 200;
        velocity.x = 300;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
