package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.staff.Personal;

public  class DBConnector {

	
	private static final String USER = "root";
	private static final String PASS = "123456";
	private static final String IP = "localhost";
	private static final String PORT = "3306";
	private static final String DBNAME = "biltegia";
	private static final String DB_URL = "jdbc:mysql://"+IP+":"+PORT+"/"+DBNAME;	
	
	private static Connection connection = null;
	
	public static void openLoginConnectionToDB() {
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void openConnectionToDB(String user, String password) {
		try {
			connection = DriverManager.getConnection(DB_URL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
   public static void specificConnectionToDB(Personal personal)
   {
	   String rol = personal.getPuesto();
	   
	   switch (rol) {
		case "mantenimiento": openConnectionToDB("root", "123456");break;
		case "operario": openConnectionToDB("root", "123456"); break;
		case "estibador": openConnectionToDB("root", "123456"); break;
		case "jefe": openConnectionToDB("root", "123456"); break;
		default:
			openConnectionToDB("root", "123456");
			break;
	   }
   }
   
   
   public static void disconnectFromDB()
   {
	   connection = null;
   } 
	
	public static ResultSet executeQuery(String query) {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public static ResultSet executeQuery(PreparedStatement stmt) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static int insertQuery(PreparedStatement stmt) {
		try {
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int insertQuery(String query) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	

	public static PreparedStatement getPreparedStatement(String string) {
		
		try {
			return connection.prepareStatement(string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}