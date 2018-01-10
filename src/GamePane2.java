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


public class GamePane2 extends JPanel implements ActionListener{
	Player player;
	JFrame container;
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

	JButton restart;

	public GamePane2(Player p) {
		super();
		setLayout (null);
		setSize(900, 600);
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
		//container = frame;

		player = p; 

		try {
			background = ImageIO.read(new File("Background.png"));
		} catch (IOException e) {
			System.out.println("whoops");
		}

		donut = new GraphicDonut(player.getDonutSelection());
		surface = new GraphicSurface();
		obstacles.add( new GraphicCarrot() );
	}

	public GraphicDonut getGraphicDonut(){
		return donut;
	}

	int frameCount = 0;

	//this is called every frame
	private void generateObstacle ()
	{
		//generates Carrots
		if ((int) ( Math.random() * 200) == 1) {
			obstacles.add(new GraphicCarrot());
		}

		//generates Broccoli
		if ((int) (Math.random() * 100) == 1) {
			obstacles.add(new GraphicBroccoli());
		}
		frameCount ++;
	}

	boolean dead = false;
	public void paintComponent(Graphics g) {
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
		for (int i = 0; i < obstacles.size(); i++) {
			if (donut.collide(obstacles.get(i))) {
				obstacles.remove(i);
				player.minusLife();
				lives.setIcon(new ImageIcon ("CandyLife" + player.getLives() + ".png"));
				if (player.numLives == 0) {
					add(restart);
					restart.addActionListener(this);
				}
			}
		}

		//draws all the obstacles
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(g);
		}
		donut.draw(g);	
		surface.draw(g);

	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(restart)) {
			System.out.println("restart");
		//	container.setContentPane(new GamePane2(player, container));
		}
	}
}

