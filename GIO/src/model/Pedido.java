package model;

import java.util.List;


public class Pedido {

    int id;
    String fecha;
    String direccion;
    Cliente cliente;
    List<Producto> listaDeProductos;

    public Pedido(int id, String fecha, String direccion, Cliente cliente, List<Producto> listaDeProductos) {
        this.id = id;
        this.fecha = fecha;
        this.direccion = direccion;
        this.cliente = cliente;
        this.listaDeProductos = listaDeProductos;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getListaDeProductos() {
        return listaDeProductos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", direccion='" + direccion + '\'' +
                ", cliente=" + cliente +
                ", listaDeProductos=" + listaDeProductos +
                '}';
    }
}
