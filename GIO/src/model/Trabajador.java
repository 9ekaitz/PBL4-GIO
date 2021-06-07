package model;

public class Trabajador {
	
	String nombre;
	String apellidos;
	String dni;
	String usuario;
	String passwd;
	String puesto;
	
	public Trabajador(String nombre, String apellidos, String dni,String usuario,String passwd, String puesto) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.usuario=usuario;
		this.passwd=passwd;
		this.puesto = puesto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getDNI() {
		return dni;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getPuesto() {
		return puesto;
	}
	
	

}
