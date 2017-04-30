package GameView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by cristiana on 30-04-2017.
 */

public class MainMenu extends State {
    private Texture background;
    private Texture playbtn;
    private Texture instructions;
    private Texture options;


    public MainMenu(GameStateManager gsm) {
        super(gsm);
        background=new Texture("backg.png");
        playbtn= new Texture("playbtn.png");
        instructions = new Texture("bi.png");
        options = new Texture("bir.png");
    }

    @Override
    public void handleinput() {

        mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.unproject(mouse);

        if(Gdx.input.isTouched()){
            //   System.out.println(playbtn.getHeight());
            //   System.out.println(playbtn.getWidth());

            //   System.out.println(Gdx.input.getX());
            //   System.out.println(Gdx.input.getY());


            if (mouse.x>= 175 && mouse.x <=(175+playbtn.getWidth()) &&
                    mouse.y>=150 && mouse.y <=(150 + playbtn.getHeight())){
                System.out.println("carrega");
                gsm.set(new PlayState(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleinput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, ViewMain.WIDTH, ViewMain.HEIGHT);
        sb.draw(playbtn, 175, 150);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
        instructions.dispose();
        options.dispose();
    }
}
