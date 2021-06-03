package modelo;

public class Personal {
	
	private String dni, nombre, apellido, rol;
	
	public Personal(String dni, String nombre, String apellido, String rol) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
	}


	public String getDni() {
		return dni;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getRol() {
		return rol;
	}
	
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	@Override
	public String toString() {
		return nombre +" " + apellido;
	}
	
	
	
}