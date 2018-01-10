import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * GraphicObstacle
 * abstract class for creating graphic obstacles
 */
public abstract class GraphicObstacle {
	private int x, y, movSpeed;
	protected BufferedImage img;
	boolean passed = false;
	boolean destroyed = false;
	String name;

	/**
	 * Constructor - defines move speed, name, and starting position
	 * @param name
	 * @param x
	 * @param y
	 * @param movSpeed
	 */
	public GraphicObstacle(String name, int x, int y, int movSpeed) {

		this.x = x;
		this.y = y;
		this.movSpeed = movSpeed;
		this.name = name;
	}

	/**
	 * Checks whether obstacle is on screen
	 * @return
	 */
	public boolean isOnScreen() {
		if (x < 0 ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the move speed of the obstacle
	 * @param speed
	 */
	public void setMSpeed( int speed ) {
		movSpeed = speed;
	}

	/**
	 * Returns the current X position
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the current Y position
	 * @return
	 */
	public int getY() {
		return y;	
	}

	/**
	 * Returns the width
	 * @return
	 */
	public int getWidth() {
		return img.getWidth();
	}

	/**
	 * Returns the height
	 * @return
	 */
	public int getHeight() {
		return img.getHeight();
	}
	
	/**
	 * Returns whether the object has been passed by the player
	 * @return
	 */
	public boolean isPassed() {
		return passed;
	}
	
	/**
	 * Marks object as passed
	 */
	public void pass() {
		passed = true;
	}
	
	/**
	 * Returns X the coordinate of the right-bounds on the object
	 * @return
	 */
	public int getFarX() {
		return x+ img.getWidth();
	}
	
	/**
	 * Returns the name of the obstacle
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * Called on panel repaint
	 * @param g
	 */
	public void draw(Graphics g) {
		//Move Left constantly
		if (! destroyed) {
			x -= movSpeed;
			g.drawImage(img, x, y, null );
		}
	}
}
