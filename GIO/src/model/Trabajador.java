package model;

public class Trabajador {
	
	String nombre;
	String apellidos;
	String dni;
	String puesto;
	
	public Trabajador(String nombre, String apellidos, String dni, String puesto) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
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
	public String getPuesto() {
		return puesto;
	}
	
	

}
