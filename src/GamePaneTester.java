import java.awt.Dimension;

import javax.swing.JFrame;

public class GamePaneTester {

	public static void main(String args[])
	{
		JFrame frame = new JFrame();
		frame.setSize(new Dimension (900,600));
		frame.setVisible(true);
		
		Player p = new Player();
		GamePane2 game = new GamePane2(p);
		frame.getContentPane().add(game);	
		frame.repaint();
	}
}
