package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
	Codificar codificar;
	PreparedStatement preparedStatement;
	ResultSet resultSet;	
	private static final String VALIDACION[] = {"cancel.png", "checked.png"};
	
	
	public ControladorLogin(Vista vista, PantallaLogin pantallaLogin) {
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
									Personal personal = new Personal(dni, nombre, apellido, rol);									
									if (personal != null) {
										JOptionPane.showMessageDialog(null, "Bienvenido a GIO inventario, " + nombre, "Inicio de sesión", 0, new ImageIcon(Recurso.PATH_ICONOS+VALIDACION[1]));
										vista.dispose();
										DBConnector.openConnectionToDB("root", "123456");
										DBConnector.specificConnectionToDB(personal);
										ApplicationFrame app = new ApplicationFrame();
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
