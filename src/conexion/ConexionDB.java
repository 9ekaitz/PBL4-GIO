package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import modelo.Personal;

public class ConexionDB {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String IP = "localhost";
	private static final String PORT = "3306";
	private static final String USER = "prueba";
	private static final String PWD = "Kaixo-123";
	private static final String DBNAME = "biltegia";
	private static final String URL = "jdbc:mysql://"+IP+":"+PORT+"/"+DBNAME;	
	
	Connection conexion;
	
	
	public boolean conectarGeneral()
	{
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL, USER, PWD);
			if (conexion != null) return true;
		} catch (SQLException  | ClassNotFoundException e) {e.printStackTrace();}
		
		return false;
	}
	
	
	public boolean conectarRol(String usuario, String clave)
	{
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL, usuario, clave);
			if (conexion != null) return true;
		} catch (SQLException  | ClassNotFoundException e) {e.printStackTrace();}
		
		return false;
	}
	
	
	
	public boolean conectarEspecifico(Personal personal)
	{
		String rol = personal.getRol();
		boolean estado = false;
		
		switch (rol) {
			case "mantenimiento":estado = conectarRol("mantenimiento", "Kaixo-123");break;
			case "operario": estado = conectarRol("operario", "Kaixo-123"); break;
			case "estibador": estado = conectarRol("estibador", "Kaixo-123"); break;
			case "jefe": estado = conectarRol("jefe", "Kaixo-123"); break;			
		}
		
		return estado;
	}
	
	
	
	public boolean desconectar()
	{
		conexion = null;
		if (conexion == null) return true;
		
		return false;
	}
	
	
	public Connection getConexion() {
		return conexion;
	}
	
	
	
}
