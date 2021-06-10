package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			e.printStackTrace();
		}
		return null;
	}
	
}
