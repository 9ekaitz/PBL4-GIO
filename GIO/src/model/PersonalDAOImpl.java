package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import connector.DBConnector;

public class PersonalDAOImpl extends AbstractListModel<Trabajador> implements PersonalDAO{
	
	List<Trabajador>listaPersonal;
	PreparedStatement preparedStatement;
	
	public PersonalDAOImpl() {
		this.listaPersonal = new ArrayList<>();
		preparedStatement=DBConnector.getPreparedStatement("INSERT INTO langilea VALUES (?,?,?,?,?,?)");
		recolectarDatos();
		fireContentsChanged(listaPersonal, 0, getSize());
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
				String usuario=datos.getString("erabiltzailea");
				String passwd="";
				listaPersonal.add(new Trabajador(nombre, apellidos, dni, usuario, passwd, puesto));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void crearTrabajador(Trabajador t) {
		listaPersonal.add(t);
		PreparedStatement aux=preparedStatement;
		try {
			aux.setString(1, t.getDNI());
			aux.setString(2, t.getNombre());
			aux.setString(3, t.getApellidos());
			aux.setString(4, t.getUsuario());
			aux.setString(5, t.getPasswd());
			aux.setString(6, t.getPuesto());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnector.insertQuery(aux);
		fireContentsChanged(listaPersonal, 0, getSize());
	}

	@Override
	public Trabajador getElementAt(int arg0) {
		return 	listaPersonal.get(arg0);
	}

	@Override
	public int getSize() {
		return listaPersonal.size();
	}
	
}
