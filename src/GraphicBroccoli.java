import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Graphic Broccoli
 * for creating graphic broccoli objects
 */
public class GraphicBroccoli extends GraphicObstacle{

	public GraphicBroccoli(){
		super("Broccoli", 1600, 450, 7);	
		
		try {
			img = ImageIO.read(new File("Broccoli.png"));
		} catch (IOException e) {
			System.out.println("whoops");
		}
		
	}

}


