
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * A JPanel component for the controls pane
 */
public class ControlsPane extends JPanel implements ActionListener {
	JButton back, option;
	DonutRoller container;

	/**
	 * Constructor sets the width and the height
	 * @param width
	 * @param height
	 * @param container
	 */
	public ControlsPane (int width, int height, DonutRoller container) {
		this.container = container;
		setSize(new Dimension (width, height));
		setBackground(new Color(245,222,179));
		setLayout(null);

		back = new JButton();
		back.setSize(new Dimension(150, 75));
		back.setIcon(new ImageIcon("BackButton.png"));
		back.setLocation(new Point(50, 450));

		add(back);
		back.addActionListener(this);
	}

	/**
	 * Overrides the paintComponents Method
	 */
	public void paintComponent(Graphics g) {
		try {
			g.drawImage(ImageIO.read(new File("ControlsScreen.png")), 0, 0, this);
		} catch (Exception e) {}
	}

	/**
	 * Implements the actionPerformed Method
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(back))
		{
			System.out.println("back");
			container.switchScreen(3);
		}
	}
}


