package screens.panel;

import renders.RenderPedido;
import screens.templates.CustomPanel;

import javax.swing.*;

import connector.DBConnector;
import gfx.ResourceHandler;
import model.ListaPedidos;
import model.Pedido;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderScreen extends CustomPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JList<Pedido> listaPedidos;
    ListaPedidos modelo;	

    public OrderScreen() {
    	super(ResourceHandler.getBackground(),new BorderLayout());
    	
    	 this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	 modelo = new ListaPedidos();
         JLabel pedido = new JLabel("Pedidos:");
         pedido.setFont(new Font("Ariel", Font.BOLD, 25));
         pedido.setForeground(Color.WHITE);
         this.add(pedido, BorderLayout.NORTH);
         
         this.add(crearPanelLista(),BorderLayout.CENTER);
         this.add(crearBotones(), BorderLayout.EAST);
    }

    private Component crearBotones() {
        JPanel panel = new JPanel(new GridLayout(2,2,10,10));
        
        panel.setOpaque(false);

        JButton bAceptar = new JButton("Aceptar");
        bAceptar.addActionListener(this);
        bAceptar.setActionCommand("aceptar");

        JButton bDenegar = new JButton("Denegar");
        bDenegar.addActionListener(this);
        bDenegar.setActionCommand("denegar");

        panel.add(bAceptar);
        panel.add(bDenegar);
        return panel;
    }

    private Component crearPanelLista() {
        JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.setPreferredSize(new Dimension(1500,820));
        
        panel.setOpaque(false);
        
        listaPedidos = new JList<>();
        listaPedidos.setCellRenderer(new RenderPedido());
        listaPedidos.setModel(modelo);
        listaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
        panel.setViewportView(listaPedidos);
        return panel;
    }

    public JList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Pedido p = (Pedido) listaPedidos.getSelectedValue();
        int id = p.getId();

        switch(e.getActionCommand()){
            case "aceptar":
                DBConnector.insertQuery("CALL acceptOrder("+ id + ")");
                break;
            case "denegar":
                DBConnector.insertQuery("CALL rejectOrder("+ id + ")");
                break;
        };
	modelo.remove(p);
    }
}
