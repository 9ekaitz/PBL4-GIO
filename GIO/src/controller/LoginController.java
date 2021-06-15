package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import connector.DBConnector;
import model.Codificar;
import model.Recurso;
import model.staff.Personal;
import screens.frame.ApplicationFrame;
import screens.frame.LoginFrame;
import screens.panel.LoginScreen;

public class LoginController implements ActionListener {

	LoginFrame vista;
	LoginScreen pantallaLogin;
	Codificar codificar;
	PreparedStatement preparedStatement;
	ResultSet resultSet;	
	private static final String VALIDACION[] = {"cancel.png", "checked.png"};
	
	
	public LoginController(LoginFrame vista, LoginScreen pantallaLogin) {
		this.vista = vista;
		this.pantallaLogin = pantallaLogin;
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
								resultSet = DBConnector.executeQuery("SELECT * FROM `langilea` WHERE erabiltzailea=\""+user+"\" AND pass=\""+pass+"\"");
								
								if (resultSet.next()) {									
									String dni = resultSet.getString("nan");
									String nombre = resultSet.getString("izena");
									String apellido = resultSet.getString("abizena");
									String rol = resultSet.getString("rol");	
									String usuario = resultSet.getString("erabiltzailea");
									Personal personal = new Personal(nombre,apellido,dni,usuario,pass,rol);									
									if (personal != null) {
										JOptionPane.showMessageDialog(null, "Bienvenido a GIO inventario, " + nombre, "Inicio de sesión", 0, new ImageIcon(Recurso.PATH_ICONOS+VALIDACION[1]));
										vista.dispose();
										if (vista.getConnection().isConnected()) {
											vista.getConnection().closeSerial();
										}
										//DBConnector.specificConnectionToDB(personal);
										ApplicationFrame app = new ApplicationFrame(personal);
									}
								} else {
									JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto", "Error! inicio de sesión", 0,new ImageIcon(Recurso.PATH_ICONOS+VALIDACION[0]));
								}
							} catch (SQLException e1) {e1.printStackTrace();}
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error, inicio de sesión", 0,new ImageIcon(Recurso.PATH_ICONOS+VALIDACION[0]));
			}									
		}			
	}
	
	

}
