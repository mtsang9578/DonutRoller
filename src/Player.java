import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Player
 * a class to keep track of player stats
 */
public class Player {
	int numLives = 3;
	int numObstacles;
	int points = 0;
	int highScore;
	String donutSelection;


	/**
	 * Constructor
	 * Sets Donut1 as the default avatar
	 */
	public Player() {
		donutSelection = "Donut1.png";
	}

	/**
	 * Changes the donut avatar
	 * @param donutSelection
	 */
	public void setDonutSelection(String donutSelection) {
		this.donutSelection = donutSelection;
	}

	/**
	 * Returns the selected donut avatar
	 * @return
	 */
	public String getDonutSelection() {
		return donutSelection;
	}

	/**
	 * Increases the number of lives
	 */
	public void addLife() {
		numLives++;
	}
	
	/**
	 * Increases the player score
	 * @param num
	 */
	public void addPoints (int num) {
		points += num;
	}

	/**
	 * Subtracts a life
	 */
	public void minusLife() {
		numLives--;
	}

	/**
	 * Increases the obstacle passed count
	 */
	public void passObstacle() {
		numObstacles ++;
	}

	/**
	 * Returns the number of lives the player has
	 */
	public int getLives() {
		return numLives;
	}

	/**
	 * Returns the points the player has
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Resets the player's stats for a new game
	 */
	public void reset() {
		points = 0;
		numLives = 3;
		numObstacles = 0;
	}
}


