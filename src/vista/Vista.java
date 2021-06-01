package vista;

import temperatura.Controlador;
import temperatura.Grafico;
import temperatura.Temperatura;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Vista implements PropertyChangeListener{

    Temperatura temp;
    JFrame ventana;
    JLabel temperatura;
    Controlador controlador;
    Grafico graf;

    public Vista() {
        ventana = new JFrame("Temperatura");
        temp = new Temperatura();
        graf = new Grafico();
        controlador = new Controlador(this);
        temp.addPropertyChangeListener(this);
        ventana.setSize(1280, 720);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //ventana.setResizable(false);
        ventana.setContentPane(crearPanelVentana());
        ventana.setVisible(true);
    }

    private Container crearPanelVentana() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(400,300));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        temperatura = new JLabel(temp.toString());
        temperatura.setBorder(new EmptyBorder(50, 100, 20, 100));
        temperatura.setFont(new Font("Arial", Font.BOLD, 60));
        temperatura.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add("East", temperatura);
        panel.add(crearPanelGrafico(),BorderLayout.CENTER);
        panel.add(crearPanelBotones(),BorderLayout.SOUTH);

        return panel;
    }

    private Component crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(1,3,20,35));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 10, 20, 10));

        JButton botonActivar = new JButton("Activar ventilador");
        botonActivar.setPreferredSize(new Dimension(100,50));
        botonActivar.setFont(new Font("Arial", Font.BOLD, 20));
        botonActivar.setActionCommand("act");
        botonActivar.addActionListener(controlador);

        JButton botonDesactivar = new JButton("Desactivar ventilador");
        botonDesactivar.setPreferredSize(new Dimension(100,50));
        botonDesactivar.setFont(new Font("Arial", Font.BOLD, 20));
        botonDesactivar.setActionCommand("des");
        botonDesactivar.addActionListener(controlador);

        JButton botonNotificar = new JButton("Notificar problema");
        botonNotificar.setPreferredSize(new Dimension(100,50));
        botonNotificar.setFont(new Font("Arial", Font.BOLD, 20));
        botonNotificar.setActionCommand("not");
        botonNotificar.addActionListener(controlador);

        panel.add(botonActivar);
        panel.add(botonDesactivar);
        panel.add(botonNotificar);

        return panel;
    }

    private Component crearPanelGrafico() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel.add(graf);
        return panel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String property = evt.getPropertyName();

        if ("Cambiado".equals(property)) {
            temperatura.setText((String) evt.getNewValue());
        }
    }
}

