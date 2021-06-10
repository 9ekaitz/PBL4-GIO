package renders;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceHandler {

	private final static String BACKGORUND_PATH = "res/1080/panel-fondo.png";
	private final static String TOP_BAR_PATH = "res/1080/topBar.png";
	public static Image background, topBarBG;
	
	public static void initGFXRes() {
		loadResources();
	}
	
	private static void loadResources() {
		try {
			background = ImageIO.read(new File(BACKGORUND_PATH));
			topBarBG = ImageIO.read(new File(TOP_BAR_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Image getBackground() {
		return background;
	}
	
	public static Image getTopBar() {
		return topBarBG;
	}
}
