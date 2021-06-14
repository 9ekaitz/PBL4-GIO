package gfx;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceHandler {

	public static final String ADD_PATH_STRING = "res/add.png";
	public static final String CREATE_PATH_STRING = "res/create.png";
	public final static String LOGO_PATH = "res/icons/GIOicon.png";
	public final static String USER_ICON_PATH= "res/icons/user.png";
	public final static String PEDIDOS_PATH = "res/1080/pedidos.png";
	public final static String PEDIDOS_ROLL_PATH = "res/1080/pedidos-roll.png";
	public final static String STOCK_PATH = "res/1080/stock.png";
	public final static String STOCK_ROLL_PATH = "res/1080/stock-roll.png";
	public final static String TEMP_PATH = "res/1080/temp.png";
	public final static String TEMP_ROLL_PATH = "res/1080/temp-roll.png";
	public final static String USERS_PATH = "res/1080/users.png";
	public final static String USERS_ROLL_PATH = "res/1080/users-roll.png";

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