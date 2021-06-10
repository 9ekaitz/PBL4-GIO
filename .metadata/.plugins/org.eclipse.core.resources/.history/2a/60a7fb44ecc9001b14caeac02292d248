package model;


import javax.swing.*;

import connector.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaPedidos extends AbstractListModel<Pedido> {

	private static final long serialVersionUID = 1L;
		List<Pedido> list;

        public ListaPedidos() {
            list = new ArrayList<>();
            añadirPedidos();
        }

        public void añadirPedidos() {

            ResultSet values = DBConnector.executeQuery(" SELECT * FROM eskaera_onartzeko e JOIN bezeroa b ON e.bezeroa = b.idBezeroa;");
            try {
            while(values.next()){
                   List<Producto> lista = new ArrayList<>();

                    int eskaeraId = values.getInt("eskaeraID");

                    ResultSet listaproductos = DBConnector.executeQuery("SELECT k.deskribapena, l.kopurua, l.prezioa FROM lerroak_onartzeko l JOIN kutxa_mota k ON l.motaId = k.motaId AND l.lehengaiaId = k.lehengaiaId WHERE l.eskaeraId  = " + eskaeraId +" ;");
                    while (listaproductos.next()){
                        Producto p = new Producto(listaproductos.getString("deskribapena"),listaproductos.getInt("kopurua"),listaproductos.getFloat("prezioa"));
                        lista.add(p);
                    }
                    Pedido pedido = new Pedido(values.getInt("eskaeraId"),values.getString("sortze_data"),values.getString("helbidea"),new Cliente(values.getInt("idBezeroa"),values.getString("cif"),values.getString("izena"),values.getInt("telefonoa")),lista);
                    list.add(pedido);
            } } catch (SQLException throwables){
                throwables.printStackTrace();
            }

            this.fireContentsChanged(list, 0, list.size());
        }
        
        public void remove(Pedido p)
        {
        	list.remove(p);
        	fireContentsChanged(list, 0, getSize());
        }
        

        @Override
        public Pedido getElementAt(int a) {
            return list.get(a);
        }

        @Override
        public int getSize() {
            return list.size();
        }

}
