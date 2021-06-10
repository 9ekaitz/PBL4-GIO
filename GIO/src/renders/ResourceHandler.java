package renders;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceHandler {

	private final static String LOGIN_BACKGROUND_PATH = "res/720/backgrounds/login-background.png";
	private final static String BACKGORUND_PATH = "res/1080/backgrounds/panel-fondo.png";
	private final static String TOP_BAR_PATH = "res/1080/backgrounds/topBar.png";
	public static Image background, topBarBG, loginBackground;
	
	public static void initGFXRes() {
		loadResources();
	}
	
	private static void loadResources() {
		try {
			background = ImageIO.read(new File(BACKGORUND_PATH));
			topBarBG = ImageIO.read(new File(TOP_BAR_PATH));
			loginBackground = ImageIO.read(new File(LOGIN_BACKGROUND_PATH));
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
	
	public static Image getLoginBackground() {
		return loginBackground;
	}
}
