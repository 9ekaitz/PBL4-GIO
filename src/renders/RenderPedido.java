package renders;

import javax.swing.*;

import model.Pedido;
import model.Producto;

import java.awt.*;

public class RenderPedido implements ListCellRenderer<Pedido> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Pedido> list, Pedido value, int index, boolean isSelected, boolean cellHasFocus) {

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(panelInfo(value), BorderLayout.CENTER);

        if(isSelected) panel.setBorder(BorderFactory.createLineBorder(Color.RED));
        return panel;
    }

    private Component panelInfo(Pedido value) {
        JPanel panel = new JPanel(new GridLayout(4,1,10,10));

        JLabel cliente =new JLabel(value.getCliente().getNombre());
        cliente.setFont(new Font("Arial",Font.ITALIC,18));
        panel.add(cliente);

        for(Producto p : value.getListaDeProductos()) {
            JLabel producto = new JLabel(String.valueOf(p.getDescripcion() + " Cantidad: " + p.getCantidad()));
            panel.add(producto);
        }

        return panel;

    }
}
