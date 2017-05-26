package GameLogic;

/**
 * Created by cristiana on 24-05-2017.
 */

public class Score implements Comparable<Score> {
    private String playerName;
    private int points;

    public Score(String name, int points) {
        this.playerName = name;
        this.points = points;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getPlayerPoints() {
        return this.points;
    }

    @Override
    public int compareTo(Score s) {
        if(this.points < s.points)
            return 1;
        else if(this.points > s.points)
            return -1;
        else
            return 0;
    }
}
