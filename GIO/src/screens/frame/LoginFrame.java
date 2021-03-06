package screens.frame;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import connector.DBConnector;
import gfx.ResourceHandler;
import hardware.Receiver;
import hardware.SERIALConnector;
import hardware.Transmiter;
import model.staff.Personal;
import screens.panel.LoginScreen;

public class LoginFrame extends JFrame{


	private static final long serialVersionUID = 1L;

	Personal personal;
	SERIALConnector connection = null;
	Transmiter transmiter = null;
	Receiver receiver = null;
	Thread threadReceiver = null;
	
	
	
	public LoginFrame() {
		ResourceHandler.initGFXRes();
		DBConnector.openLoginConnectionToDB();
		this.setTitle("GIO");
		this.setSize(1200, 720);
		this.setLocationRelativeTo(null);;
	
		/*INICIO Cargar pantallas */

		serialConnectionStart();		
		this.setContentPane(new LoginScreen(this));
		
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
		connection = new SERIALConnector();
		if (connection.isConnected()) {
			transmiter = new Transmiter(connection);
			receiver = new Receiver(connection);
			if (receiver != null) {
				threadReceiver = new Thread(receiver);
				threadReceiver.start();
			}
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

	public Transmiter getTransmiter() {
		return transmiter;
	}
	
	public SERIALConnector getConnection() {
		return connection;
	}
	
	/*FIN GET & SETT variables*/
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		
		LoginFrame principal = new LoginFrame();
	}
}
