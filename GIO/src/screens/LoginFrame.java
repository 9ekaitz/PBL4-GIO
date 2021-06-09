package vista;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import connector.DBConnector;
import gio.gfx.ResourceHandler;
import hardware.Receiver;
import hardware.SERIALConnector;
import hardware.Transmiter;
import modelo.Personal;

public class Vista extends JFrame{


	private static final long serialVersionUID = 1L;
	private static final String SERIAL_PORTNAME = "COM4";
	Personal personal;
	SERIALConnector connection;
	Transmiter transmiter;
	Receiver receiver;
	Thread threadReceiver;
	
	
	
	public Vista() {
		ResourceHandler.initGFXRes();
		DBConnector.openLoginConnectionToDB();
		this.setTitle("GIO");
		this.setSize(1200, 720);
		this.setLocationRelativeTo(null);;
	
		/*INICIO Cargar pantallas */
		receiver = null;
		transmiter = null;
		serialConnectionStart();
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
	
	
	public void serialConnectionStart()
	{
		connection = new SERIALConnector(SERIAL_PORTNAME);
		if (connection.isConnected()) {
			transmiter = new Transmiter(connection);
			receiver = new Receiver(connection);
			threadReceiver = new Thread(receiver);
			threadReceiver.start();
		}
	}
	
	
	/*INICIO GET & SETT variables*/
	
	public Personal getPersonal() {
		return personal;
	}
	
	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	
	
	public Receiver getReceiver() {
		return receiver;
	}

	/*FIN GET & SETT variables*/
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		
		Vista principal = new Vista();
	}
}
