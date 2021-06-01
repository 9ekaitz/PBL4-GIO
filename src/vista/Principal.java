package vista;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {
	
	Conexion conexion;
	Connection connection;
	Vista vista_temperatura;
	//Statement statement;
	//Resultset resultset;
	
	public Principal() {
		conexion = new Conexion();
		vista_temperatura = new Vista();
	}
	
	
	public void db() {
		connection = conexion.getConexion();
		try {
			Statement  statement= (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = (ResultSet) statement.executeQuery("SELECT * FROM tenperatura");
			
			/*while(resultSet.next()){
				vista_temperatura.temp.setTemperatura(Float.parseFloat(resultSet.getString("tenperatura")));
				System.out.println(resultSet.getString("tenperatura"));
			}*/
			resultSet.last();
			vista_temperatura.temp.setTemperatura(Float.parseFloat(resultSet.getString("tenperatura")));
			System.out.println(resultSet.getString("tenperatura"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Principal principal = new Principal();
		principal.db();
	}

}
