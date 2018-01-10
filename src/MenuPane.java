// Sean Elia and Mya Tsang
// 4/27/17
// Advanced Object Oriented Design
// MenuPane
//
//

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * JPanel component to display the main menu
 */
public class MenuPane extends JPanel implements ActionListener {
	JButton play, option;
	DonutRoller container;

	/**
	 * Set the size of the pane and add button components to screen
	 * @param width
	 * @param height
	 * @param container
	 */
	public MenuPane (int width, int height, DonutRoller container) {
		this.container = container;
		setSize(new Dimension (width, height));
		setBackground(new Color(245,222,179));
		setLayout(null);

		play = new JButton();
		play.setSize(new Dimension(150, 75));
		play.setIcon(new ImageIcon("PlayButton.png"));
		play.setLocation(new Point(175, 400));

		option = new JButton();
		option.setSize(new Dimension(150, 75));
		option.setIcon(new ImageIcon("OptionButton.png"));
		option.setLocation(new Point(525, 400));
		
		add(play);
		play.addActionListener(this);
		add(option);
		option.addActionListener(this);
	}

	/**
	 * Set the background to be painted
	 */
	public void paintComponent(Graphics g) {
		try {
			g.drawImage(ImageIO.read(new File("MenuScreen.png")), 0, 0, this);
		} catch (Exception e) {}
	}

	@Override
	/**
	 * Responds to button presses
	 */
	public void actionPerformed( ActionEvent e) {
		
		if(e.getSource().equals(play)) {
			System.out.println("play");
			container.switchScreen(4);
		}
		
		if ( e.getSource().equals(option)) {
			System.out.println("option");
			container.switchScreen(2);
		}
	}
}


