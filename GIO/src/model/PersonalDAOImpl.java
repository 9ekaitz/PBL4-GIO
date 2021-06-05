package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.DBConnector;

public class PersonalDAOImpl implements PersonalDAO{
	
	List<Trabajador>listaPersonal;
	
	public PersonalDAOImpl() {
		this.listaPersonal = new ArrayList<>();
		recolectarDatos();
	}

	@Override
	public void add(Trabajador trabajador) {
		listaPersonal.add(trabajador);		
	}

	@Override
	public Trabajador[] getTrabajadores() {
		return listaPersonal.toArray(new Trabajador[0]);
	}
	
	public void recolectarDatos() {
		ResultSet datos=DBConnector.executeQuery("SELECT * FROM langilea");
		try {
			while(datos.next()) {
				String dni=datos.getString("nan");
				String nombre=datos.getString("izena");
				String apellidos=datos.getString("abizena");
				String puesto=datos.getString("rol");
				listaPersonal.add(new Trabajador(nombre, apellidos, dni, puesto));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
