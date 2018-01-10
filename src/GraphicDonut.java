import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * GraphicDonut
 * for creating graphic donut objects and keeping track of player stats
 */
public class GraphicDonut {
	int x = 0;
	int y = 400;
	int jSpeed = 10;
	int mSpeed = 5;
	final int MAX_HEIGHT = 300;
	int height = 0;
	boolean jump = false;
	boolean canJump = true;
	boolean movLeft = false;
	boolean movRight = false;
	boolean duck = false;
	BufferedImage donutImg;
	BufferedImage duckImg;
	BufferedImage img;
	int destroyAll = 2;

	public GraphicDonut(String donutSelection)
	{
		try {
			donutImg = ImageIO.read(new File(donutSelection));
		} catch (IOException e) {
			System.out.println("whoops");
		}

		try {
			duckImg = ImageIO.read(new File("GlazedMunchkin.png"));
		} catch (IOException e) {
			System.out.println("whoops");
		}

		img = donutImg;
	}

	public int getX() {
		return x;
	}

	public void setDonutSelection(String donutSelection)
	{
		try {
			donutImg = ImageIO.read(new File(donutSelection));
		} catch (IOException e) {
			System.out.println("whoops");
		}

	}

	public void destroyAll()
	{
		
	}
	
	public boolean collide (GraphicObstacle obs)
	{

		if ( (y + img.getHeight() < obs.getY())	//object is completely above
				|| (y > obs.getY() + obs.getHeight())  ) // object is completely beneath
		{
			return false;
		} else if ( (x + img.getWidth() < obs.getX())	//object is completely left
				|| (x > obs.getX() + obs.getWidth())  ) // object is completely right
		{
			return false;
		} else
			return true;


	}

	public void jump() {
		if (canJump)
			jump = true;
	}

	public void left(){
		movLeft = true;
	}

	public void right(){
		movRight = true;
	}

	public void cancelLeft(){
		movLeft = false;
	}

	public void cancelRight(){
		movRight = false;
	}

	public void duck(){
		if ( !duck ){
			x += 25;
			y += 50;
		}

		img = duckImg;
		duck = true;
	}

	public void cancelDuck(){
		if (duck){
			x -= 25;
			y -= 50;
		}

		img = donutImg;
		duck = false;
	}

	public int getHeight(){
		return height;
	}


	int degrees = 0;

	public void draw( Graphics g )
	{


		/**--------------------------- JUMPING ----------------------------------*/

		//if you have reached max height stop jumping
		if (height >= MAX_HEIGHT){
			jump = false;
			canJump = false;
		}

		//CALCULATE SPEED
		if (MAX_HEIGHT - height <= 10) {
			jSpeed = 1;
		} else if (MAX_HEIGHT - height <= 40) {
			jSpeed = 5;
		} else {
			jSpeed = 10;
		}



		//if you are not jumping but in the air then fall
		if (!jump && height> 0){
			if (duck)
			{
				y+=15;
				height -=15;
			} else {
				y +=jSpeed;
				height -= jSpeed;
			}
		}

		//if you are not max height and jumping then keep jumping
		else if (jump) {
			if (duck && height > 5)
			{
				jump = false;
				canJump = false;
				y+=15;
				height -=15;
			} else {
				y -=jSpeed;
				height += jSpeed;
			}
		}



		//places back at start if you get close to the ground
		if(Math.abs(height) < 10){
			y += height;
			height = 0;
		}

		//Determines whether you have landed
		if(height == 0){
			canJump = true;
			jump=false;
		} 

		/**--------------------------- MOVING ----------------------------------*/

		if (x <= 0 ){
			movLeft = false;
		} else if ( x >= 1500){
			movRight = false;
		}

		if(movLeft){
			x -= mSpeed;
		}

		if (movRight){
			x += mSpeed;
		}




		/**--------------------------- DUCK ----------------------------------*/
		if (duck){
			g.drawImage(img, x, y, null);

		} else {
			g.drawImage(img, x, y, null);

		}

	}
}



