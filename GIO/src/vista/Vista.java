package vista;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.Personal;

public class Vista extends JFrame{


	private static final long serialVersionUID = 1L;
	Personal personal;
	
	
	
	public Vista() {
		
		this.setTitle("GIO");
		this.setSize(1200, 720);
		this.setLocationRelativeTo(null);;
		
		/*INICIO Cargar pantallas */
		this.cargarPantallaLogin();
		
		
		/*FIN Cargar pantallas */
		
		/*INICIO Inicializarci�n de variables*/
		
		personal = null;
		
		/*FIN Inicializaci�n de variables*/
		
		
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/*INICIO Cargar contenido pantallas*/
	
	
	public void cargarPantallaLogin()
	{
		
		PantallaLogin pantallaLogin = new PantallaLogin(this);
		this.getContentPane().removeAll();
		this.getContentPane().add(pantallaLogin);
		this.validate();
		this.repaint();
		
	}
	
	
	public void cargarPantallaBorrador()
	{		
		PantallaBorrador pantallaBorrador = new PantallaBorrador(this);
		this.getContentPane().removeAll();
		this.getContentPane().add(pantallaBorrador);
		this.validate();
		this.repaint();
		
	}
	
	/*FIN Cargar contenido pantallas*/
	
	
	
	
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
