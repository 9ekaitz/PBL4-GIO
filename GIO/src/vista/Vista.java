package vista;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import connector.DBConnector;
import gio.gfx.ResourceHandler;
import modelo.Personal;

public class Vista extends JFrame{


	private static final long serialVersionUID = 1L;
	Personal personal;
	
	
	
	public Vista() {
		ResourceHandler.initGFXRes();
		DBConnector.openLoginConnectionToDB();
		this.setTitle("GIO");
		this.setSize(1200, 720);
		this.setLocationRelativeTo(null);;
	
		/*INICIO Cargar pantallas */
		this.setContentPane(new PantallaLogin(this));
		
		/*FIN Cargar pantallas */
		
		/*INICIO Inicializarci�n de variables*/
		
		personal = null;
		
		/*FIN Inicializaci�n de variables*/
		
		
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/*INICIO GET & SETT variables*/
	
	public Personal getPersonal() {
		return personal;
	}
	
	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	/*FIN GET & SETT variables*/
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		
		Vista principal = new Vista();
	}

}
