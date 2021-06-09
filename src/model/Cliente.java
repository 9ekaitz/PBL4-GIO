package model;

public class Cliente {

    int idCliente;
    String cif;
    String nombre;
    int telefono;

    public Cliente(int idCliente,String cif,String nombre,int telefono){
        this.idCliente = idCliente;
        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getCif() {
        return cif;
    }

    public int getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "pedido.Cliente{" +
                "idCliente=" + idCliente +
                ", cif='" + cif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
