package tmp;
import java.awt.Font;

import frame.ApplicationFrame;
import gio.gfx.ResourceHandler;

public class Principal {

	
	private static String FONT_PATH = "fonts/Oliciy.ttf";
	public static Font fuenteLogin;
	
	public static void main(String[] args) {
//		try {
//			fuenteLogin = Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH));
//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			ge.registerFont(fuenteLogin);
//		} catch (FontFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ResourceHandler.initGFXRes();
		ApplicationFrame p = new ApplicationFrame();
//		p.setPanel();
	}

}