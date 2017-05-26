package GameView;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Collections;

import GameLogic.Score;


public class FlyChicken extends Game {

    public SpriteBatch batch;

    private static FlyChicken instance = null;

    private Preferences prefs;

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Fly Chicken... FLY!";

    public ArrayList<Score> scores;

    public FlyChicken() {
        this.scores = new ArrayList<Score>();
    }

    public static FlyChicken getInstance() {
        if (instance == null) {
            instance = new FlyChicken();
        }
        return instance;
    }

    public Preferences getPrefs() {
        return prefs;
    }
    public void setPrefs(Preferences p) {
        this.prefs = p;

        for(int i=0; i<5; i++){
            int score = this.prefs.getInteger("score " + i, 0);
            String name = this.prefs.getString("name " + i, "Player "+ i);

            Score s = new Score(name,score);
            scores.add(s);
        }
    }
    public void AddScore(Score playerScore) {
        scores.add(playerScore);
        Collections.sort(scores);

        for (int i=0; i<scores.size(); i++){
            prefs.putInteger("score "+ i, scores.get(i).getPlayerPoints());
            prefs.putString("name " + i, scores.get(i).getPlayerName());
        }

        prefs.flush();
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new MainMenu(this));
    }

    @Override
    public void render () {
        super.render();
    }


    @Override
    public void dispose () {

    }
}
