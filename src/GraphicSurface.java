import java.awt.Color;
import java.awt.Graphics;

/**
 * Graphic Surface
 * graphic object to represent the surface
 *
 */
public class GraphicSurface {
	public GraphicSurface() {}

	public void draw( Graphics g ) {
		g.setColor(new Color(255,180,190));	
		g.fillRect(0, 500 , 900 , 100);
	}
}


