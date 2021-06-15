package gfx;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceHandler {

	public static final String ADD_PATH_STRING = "/icons/add.png";
	public static final String CREATE_PATH_STRING = "/icons/create.png";
	public final static String LOGO_PATH = "/icons/GIOicon.png";
	public final static String USER_ICON_PATH= "/icons/user.png";
	public final static String PEDIDOS_PATH = "/1080/pedidos.png";
	public final static String PEDIDOS_ROLL_PATH = "/1080/pedidos-roll.png";
	public final static String STOCK_PATH = "/1080/stock.png";
	public final static String STOCK_ROLL_PATH = "/1080/stock-roll.png";
	public final static String TEMP_PATH = "/1080/temp.png";
	public final static String TEMP_ROLL_PATH = "/1080/temp-roll.png";
	public final static String USERS_PATH = "/1080/users.png";
	public final static String USERS_ROLL_PATH = "/1080/users-roll.png";
	public final static String BA_PATH = "/1080/balanza.png";
	public final static String BA_ROLL_PATH = "/1080/balanza-roll.png";

	private final static String LOGIN_BACKGROUND_PATH = "720/backgrounds/login-background.png";
	private final static String BACKGORUND_PATH = "1080/backgrounds/panel-fondo.png";
	private final static String TOP_BAR_PATH = "1080/backgrounds/topBar.png";
	private final static String WEIGHT_BACKGROUND_PATH = "720/backgrounds/weight-background.png";
	
	
	public static Image background, topBarBG, loginBackground, weightBackground;
	
	public static void initGFXRes() {
		loadResources();
	}
	
	private static void loadResources() {
		ClassLoader c = ClassLoader.getSystemClassLoader();
		try {
			background = ImageIO.read(c.getResource((BACKGORUND_PATH)));
			topBarBG = ImageIO.read(c.getResource(TOP_BAR_PATH));
			loginBackground = ImageIO.read(c.getResource(LOGIN_BACKGROUND_PATH));
			weightBackground = ImageIO.read(c.getResource(WEIGHT_BACKGROUND_PATH));
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
	
	public static Image getWeightBackground() {
		return weightBackground;
	}
}
