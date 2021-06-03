package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import conexion.ConexionDB;
import connector.DBConnector;
import frame.ApplicationFrame;
import modelo.Codificar;
import modelo.Personal;
import modelo.Recurso;
import vista.PantallaLogin;
import vista.Vista;

public class ControladorLogin implements ActionListener {

	Vista vista;
	PantallaLogin pantallaLogin;
	Connection conexion;
	ConexionDB conexionDB;
	Codificar codificar;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	private final String query = "SELECT * FROM `langilea` WHERE erabiltzailea=? AND pass=?";
	
	private static final String VALIDACION[] = {"cancel.png", "checked.png"};
	
	
	public ControladorLogin(Vista vista, PantallaLogin pantallaLogin) {
		this.vista = vista;
		this.pantallaLogin = pantallaLogin;
		conexionDB = new ConexionDB();
		codificar = new Codificar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		String comando = (String) e.getActionCommand();
		
		if (comando.equals("btnConectar")) {
			
	
			if (!pantallaLogin.getUsuarioCampo().toString().isEmpty() && !String.valueOf(pantallaLogin.getClaveCampo().getPassword()).isEmpty()) {
						try {
								String user =  pantallaLogin.getUsuarioCampo().getText().toString();
								String pass = codificar.codificar(String.valueOf(pantallaLogin.getClaveCampo().getPassword()));
//								preparedStatement = conexion.prepareStatement(query);
//								preparedStatement.setString(1, pantallaLogin.getUsuarioCampo().getText().toString());
//								preparedStatement.setString(2, codificar.codificar(String.valueOf(pantallaLogin.getClaveCampo().getPassword())));							
//								resultSet = preparedStatement.executeQuery();
								resultSet = DBConnector.executeQuery("SELECT * FROM `langilea` WHERE erabiltzailea=\""+user+"\" AND pass=\""+pass+"\"");
								
								if (resultSet.next() || true) {									
//									String dni = resultSet.getString("nan");
//									String nombre = resultSet.getString("izena");
//									String apellido = resultSet.getString("abizena");
//									String rol = resultSet.getString("rol");																
//									Personal personal = new Personal(dni, nombre, apellido, rol);
									Personal personal = null;
									String nombre = "eka";
									
									if (personal != null || true) {
										JOptionPane.showMessageDialog(null, "Bienvenido a GIO inventario, " + nombre, "Inicio de sesi�n", 0, new ImageIcon(Recurso.PATH_ICONOS+VALIDACION[1]));
										vista.dispose();
										DBConnector.openConnectionToDB("root", "123456");		//TODO integratu login rolen arabera
										ApplicationFrame app = new ApplicationFrame();
									}
								} else {
									JOptionPane.showMessageDialog(null, "Usuario o contrase�a incorrecto", "�Error! inicio de sesi�n", 0,new ImageIcon(Recurso.PATH_ICONOS+VALIDACION[0]));
								}
							} catch (SQLException e1) {e1.printStackTrace();}
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contrase�a incorrectos", "�Error! inicio de sesi�n", 0,new ImageIcon(Recurso.PATH_ICONOS+VALIDACION[0]));
			}										
		}			
	}
	
	

}
