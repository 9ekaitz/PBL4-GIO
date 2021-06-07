package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

public  class DBConnector {

	private static final String DB_URL = "jdbc:mysql://localhost/biltegia";
	private static final String USER = "root";
	private static final String PASS = "12345678aA@";

	private static Connection connection = null;
	
	public static void openConnectionToDB() {
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet executeQuery(String query) {
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
<<<<<<< HEAD
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
	
	public static void insertQuery(PreparedStatement stmt) {
		try {
			stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertQuery(String query) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static PreparedStatement getPreparedStatment(String query) {
		try {
			return connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
=======
	public static void insertQuery(PreparedStatement stmt) {
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static PreparedStatement getPreparedStatement(String string) {
		
		try {
			return connection.prepareStatement(string);
		} catch (SQLException e) {
>>>>>>> refs/remotes/origin/personal
			e.printStackTrace();
		}
		return null;
	}
	
}
