import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * JPanel component for displaying the game view
 */
public class GamePane extends JPanel implements ActionListener, Runnable{
	Player player;
	DonutRoller container;
	GraphicDonut donut;
	GraphicSurface surface;
	ArrayList <GraphicObstacle> obstacles = new ArrayList <GraphicObstacle> ();
	BufferedImage background;
	boolean destroy = false;
	JLabel points, lives;
	Thread animation;

	final int WIDTH = 900;
	final int HEIGHT = 600;
	final int MARGIN = 10;

	JButton restart, back;

	/**
	 * Constructor to set up the panel dimensions and background
	 * @param p
	 * @param container
	 */
	public GamePane(Player p, DonutRoller container) {
		super();
		setLayout (null);
		Font font = new Font("Serif", Font.BOLD, 36);

		points = new JLabel ("Points: " + 0);
		points.setSize(200,36);
		points.setLocation(WIDTH - points.getWidth() - MARGIN, MARGIN);
		points.setFont(font);
		add(points);

		lives = new JLabel();
		lives.setText("");
		lives.setIcon(new ImageIcon("CandyLife3.png"));
		lives.setSize(300,48);
		lives.setLocation(WIDTH - points.getWidth() - 200 - MARGIN, MARGIN);
		lives.setFont(font);
		add(lives);

		restart = new JButton();
		restart.setSize(new Dimension(150, 75));
		restart.setIcon(new ImageIcon("RestartButton.png"));
		restart.setLocation(new Point(375, 375));

		back = new JButton();
		back.setSize(new Dimension(150, 75));
		back.setIcon(new ImageIcon("BackButton.png"));
		back.setLocation(new Point(375, 275));

		this.container = container;
		player = p; 

		try {
			background = ImageIO.read(new File("Background.png"));
		} catch (IOException e) {
			System.out.println("whoops");
		}

		donut = new GraphicDonut(player.getDonutSelection());
		surface = new GraphicSurface();
		obstacles.add(new GraphicCarrot());

	}

	/**
	 * For passing a reference of the donut object to the DonutRoller class
	 * @return
	 */
	public GraphicDonut getGraphicDonut() {
		return donut;
	}

	int frameCount = 0;

	/**
	 * Called every frame. Checks to see if an obstacle should be generated and
	 * handles the rates at which objects are generated
	 */
	private void generateObstacle () {
		//generates Lives
		if ((int)( Math.random() * 300) == 1) {
			obstacles.add(new GraphicLife());
		}
		//generates Carrots
		if ((int)( Math.random() * 200) == 1) {
			obstacles.add(new GraphicCarrot());
		}
		//generates Broccoli
		if ((int)(Math.random() * 100) == 1 ) {
			obstacles.add(new GraphicBroccoli());
		}
	}

	boolean dead = false;
	/**
	 * Overrides JPanel method for painting objects onto the screen
	 */
	public void paintComponent(Graphics g) {
		frameCount ++;
		generateObstacle();

		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, this);		


		//checks for pass and adds points
		for (int i = 0; i < obstacles.size(); i++){
			if (!obstacles.get(i).isPassed() && donut.getX() > obstacles.get(i).getFarX()) {
				player.addPoints(10);
				points.setText("Points: " + player.getPoints());
				obstacles.get(i).pass();
			}
		}
		
		//checks for collisions and removes if collision occurs
		for (int i = 0; i < obstacles.size(); i++){
			if (donut.collide(obstacles.get(i))){
				if(obstacles.get(i).getName().equals("CandyLife")) {
					player.addLife();
					obstacles.remove(i);
					setLifeLabel();
				} else {
					obstacles.remove(i);
				
					player.minusLife();
					//lives.setIcon(new ImageIcon ("CandyLife" + player.getLives() + ".png"));
					setLifeLabel();
					if(player.numLives == 0) {
						add(back);
						back.addActionListener(this);
						add(restart);
						restart.addActionListener(this);
					}
				}
			}
		}

		//draws all the obstacles
		for (int i = 0; i < obstacles.size(); i++){
			obstacles.get(i).draw(g);
		}
		
		donut.draw(g);	
		surface.draw(g);
	}

	/**
	 * Sets the life label based on the number of player lives
	 */
	private void setLifeLabel() {
		if (player.getLives() >= 0 && player.getLives() <= 3) {
			lives.setIcon(new ImageIcon("CandyLife"+player.getLives()+".png"));
			lives.setText("");
		} else {
			lives.setIcon(new ImageIcon("CandyLife1.png"));
			lives.setText(" X " + player.getLives());
		}

	}

	/**
	 * Respond to clicking buttons on the screen
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(restart)) {
			player.reset();
			container.switchScreen(1);
		}

		if (e.getSource().equals(back)) {
			player.reset();
			container.switchScreen(3);
		}
	}

	int destroys = 3;
	int destroyFrame = 0 ;
	
	/**
	 * Allows player to use the destroy all power for 10 frames 
	 * (Not currently implemented)
	 */
	public void destroyAll() {
		if (frameCount - destroyFrame > 10) {
			destroyFrame = frameCount;
			if (destroys > 0) {
				destroys--;
				for (int i= 0; i<obstacles.size(); i++)
				{	
					if (obstacles.get(i).getFarX()< 900){
						obstacles.remove(i);
						player.addPoints(10);
						points.setText("Points: " + player.getPoints());
						i--;
					}
				}
			}
		}
	}

	@Override
	/**
	 * Required method for running as a thread
	 */
	public void run() {
		// TODO Auto-generated method stub
		while(player.getLives() > 0) {
			repaint();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

