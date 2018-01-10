import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * GraphicLife
 * for creating graphic life obstacles
 */
public class GraphicLife extends GraphicObstacle {
	public GraphicLife() {
		super("CandyLife", 1600, 200, 10);	
		try {
			img = ImageIO.read(new File("CandyLife1.png"));
		} catch (IOException e) {
			System.out.println("whoops");
		}	
	}
}
