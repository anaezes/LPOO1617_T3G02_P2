package GameLogic;

/**
 * Class Score
 * <br> A class that implement methods to obtain scores
 * <br> contain a string with the name of the player and its score
 */

public class Score implements Comparable<Score> {
    private String playerName;
    private int points;

    /**
     * Class Constructor Score
     * @param name      name of player
     * @param points    score of the player
     */
    public Score(String name, int points) {
        this.playerName = name;
        this.points = points;
    }

    /**
     * Return name of player
     * @return  name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Return score of the player
     * @return  points
     */
    public int getPlayerPoints() {
        return this.points;
    }

    /**
     * Method to help sort (comparing) scores in descendent order
     * @param s     score
     * @return  true if score is greater or false otherwise
     */
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
