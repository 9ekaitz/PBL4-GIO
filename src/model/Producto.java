package model;

public class Producto {

    String descripcion;
    int cantidad;
    float precio;

    public Producto(String descripcion, int cantidad, float precio) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getDescripcion() {
		return descripcion;
	}



	public int getCantidad() {
        return cantidad;
    }
}
