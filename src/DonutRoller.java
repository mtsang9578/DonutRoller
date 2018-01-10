import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * Driver class that runs the game and handles switching between scenes
 */
public class DonutRoller implements KeyListener {
	JFrame frame;
	GamePane level1;
	Font font;
	JLabel points, lives;
	MenuPane menu;
	ControlsPane controlsPane;
	AvatarPane avatarPane;
	Player player;
	JPanel active;

	public JLayeredPane container; 
	GraphicDonut donut;
	final int WIDTH = 900;
	final int HEIGHT = 600;
	final int MARGIN = 10;
	Thread animation;
	public boolean restart =false;
	
	/**
	 * Constructor
	 */
	public DonutRoller() {
		player = new Player();

		frame = new JFrame();
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);

		menu = new MenuPane( WIDTH,HEIGHT, this);

		frame.setContentPane(menu);
		frame.pack();
		frame.addKeyListener(this);

		restart = true;
	}

	/**
	 * Switches the main view
	 * @param state
	 */
	public void switchScreen (int state) {
		if (state == 1) {
			level1 = new GamePane(player, this);
			level1.setSize(new Dimension(WIDTH,HEIGHT));
			frame.setContentPane(level1);
			donut = level1.getGraphicDonut();
			frame.requestFocus();
			
			animation = new Thread(level1);
			animation.start();
		
		} else if (state == 2) {
			controlsPane = new ControlsPane( WIDTH, HEIGHT, this);
			frame.setContentPane(controlsPane);
			frame.repaint();
			System.out.println("The state is" + state);

		} else if (state == 3) {
			menu = new MenuPane( WIDTH, HEIGHT, this);
			frame.setContentPane(menu);
			frame.repaint();
			System.out.println("The state is" + state);

		} else if (state == 4) {
			avatarPane = new AvatarPane(WIDTH, HEIGHT, this, player);
			frame.setContentPane(avatarPane);
			System.out.println("The state is" + state);
		}
	}	
	
	/**
	 * Continuously calls the repaint method to animate the game
	 */
	public void runGame() {
		while(player.getLives() > 0){	
			frame.repaint();	
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Main method to create a new instance of the Donut Roller Game
	 * @param args
	 */
	public static void main(String[] args) {
		new DonutRoller();
	}

	@Override
	/**
	 * Handle player movement on key press
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			donut.jump();
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			donut.right();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			donut.left();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			donut.duck();
		}
	}

	@Override
	/**
	 * Cancel player movement on key release
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			donut.cancelRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			donut.cancelLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			donut.cancelDuck();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			level1.destroyAll();
		}
	}

	@Override
	/**
	 * Implement required method
	 */
	public void keyTyped(KeyEvent e) {}
}



