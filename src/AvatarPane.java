import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * A JPanel component for the Avatar Screen
 */
public class AvatarPane extends JPanel implements ActionListener {
	JButton play, back;
	JButton[] donuts;
	DonutRoller container;
	Player player;
	
	/**
	 * Constructor initializes size
	 * @param width
	 * @param height
	 * @param container
	 * @param player
	 */
	public AvatarPane (int width, int height, DonutRoller container, Player player) {
		this.player = player;
		this.container = container;
		setSize(new Dimension (width, height));
		setBackground(new Color(245,222,179));
		setLayout(null);
		
		donuts = new JButton[7];
		for(int i = 0; i<donuts.length;i++) {
			donuts[i] = new JButton();
			donuts[i].setSize(new Dimension (100,100));
			donuts[i].setIcon(new ImageIcon("Donut"+ (i+1) + ".png"));
			donuts[i].setLocation( (i+1)*25 + i*100, 300);
			donuts[i].setBorderPainted(false);
			add(donuts[i]);
		}

		play = new JButton();
		play.setSize(new Dimension(150, 75));
		play.setIcon(new ImageIcon("PlayButton.png"));
		play.setLocation(50, 450);

		back = new JButton();
		back.setSize(new Dimension(150, 75));
		back.setIcon(new ImageIcon("BackButton.png"));
		back.setLocation(700, 450);


		for( int i =0; i< donuts.length; i++)
		{
			donuts[i].addActionListener(this);
		}
		add(play);
		play.addActionListener(this);
		add(back);
		back.addActionListener(this);
	}

	int xCoordinate = 25;
	
	/**
	 * Overrides paintComponentMethod
	 */
	public void paintComponent(Graphics g) {
		try {
			g.drawImage(ImageIO.read(new File("AvatarScreen.png")), 0, 0, this);
		} catch (Exception e) {}
		g.setColor(Color.CYAN);
		g.fillOval(xCoordinate - 5, 295, 110, 110);

	}

	@Override
	/**
	 * Implements actionPerformedMethod
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(play)) {
			System.out.println("play");
			container.switchScreen(1);
		}

		if (e.getSource().equals(back)) {
			System.out.println("back");
			container.switchScreen(3);
		}

		for(int i =0; i< donuts.length; i++) {
			if (e.getSource().equals(donuts[i])) {
				System.out.println("donut" + i);
				xCoordinate = 25*(i+1) + 100*i;
				player.setDonutSelection("Donut"+ (i+1) + ".png");
				repaint();
			}
		}
	}
}


