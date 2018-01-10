import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Graphic Carrot
 * for creating graphic carrot objects
 */
public class GraphicCarrot extends GraphicObstacle{

	public GraphicCarrot(){	
		super("Carrot", 1600, 340, 10);	
		
		try {
			img = ImageIO.read(new File("Carrot.png"));
		} catch (IOException e) {
			System.out.println("whoops");
		}
		
	}

}
