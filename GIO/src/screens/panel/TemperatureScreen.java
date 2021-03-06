package screens.panel;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import connector.DBConnector;
import controller.TemperatureController;
import gfx.ResourceHandler;
import model.Temperatura;
import screens.frame.ApplicationFrame;
import screens.templates.CustomPanel;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class TemperatureScreen extends CustomPanel implements PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	ApplicationFrame vista;
	Temperatura temp;
    JLabel temperatura;
    TemperatureController controlador;
    GraphicScreen graf;
    String receivedData, tempData, temp2;
    Float tempToDb;

    public TemperatureScreen(ApplicationFrame vista) {
    	super(ResourceHandler.getBackground(),new BorderLayout());
    	
    	this.vista = vista;
    	
    	this.temp = new Temperatura();
    	this.temp.addPropertyChangeListener(this);
        this.graf = new GraphicScreen();
        
       temperatura = new JLabel(temp.toString());
       temperatura.setBorder(new EmptyBorder(50, 100, 20, 100));
       temperatura.setForeground(Color.WHITE);
       temperatura.setFont(new Font("Arial",Font.BOLD, 60));
       temperatura.setHorizontalAlignment(SwingConstants.CENTER);
       
       this.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
       if (this.vista.getReceiver() != null) {
    	   this.vista.getReceiver().addPropertyChangeListener(this);
       }
       
       this.controlador = new TemperatureController(this);  
       
       this.add("East", temperatura);
       this.add(crearPanelGrafico(), BorderLayout.CENTER);
       this.add(crearPanelBotones(), BorderLayout.SOUTH);
  	
    }


    private Component crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(1,3,20,35));

        panel.setOpaque(false);
        JButton botonActivar = new JButton("Activar ventilador");
        botonActivar.setPreferredSize(new Dimension(100,50));
        botonActivar.setFont(new Font("Arial", Font.BOLD, 20));
        botonActivar.setActionCommand("act");
        botonActivar.addActionListener(controlador);
        botonActivar.setBackground(new Color(211,211,211)); 
        botonActivar.setFocusPainted(false);

        JButton botonDesactivar = new JButton("Desactivar ventilador");
        botonDesactivar.setPreferredSize(new Dimension(100,50));
        botonDesactivar.setFont(new Font("Arial", Font.BOLD, 20));
        botonDesactivar.setActionCommand("des");
        botonDesactivar.addActionListener(controlador);
        botonDesactivar.setBackground(new Color(211,211,211)); 
        botonDesactivar.setFocusPainted(false);

        JButton botonNotificar = new JButton("Notificar problema");
        botonNotificar.setPreferredSize(new Dimension(100,50));
        botonNotificar.setFont(new Font("Arial", Font.BOLD, 20));
        botonNotificar.setActionCommand("not");
        botonNotificar.addActionListener(controlador);
        botonNotificar.setBackground(new Color(211,211,211)); 
        botonNotificar.setFocusPainted(false);

        panel.add(botonActivar);
        panel.add(botonDesactivar);
        panel.add(botonNotificar);

        return panel;
    }

    private Component crearPanelGrafico() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,300));
        panel.setOpaque(false);
        panel.add(graf);
        return panel;
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
        String property = (String) evt.getPropertyName();
        
        switch(property) {
        case "Cambiado":
        	 temperatura.setText((String) evt.getNewValue());
        	 temp2 =String.valueOf(evt.getNewValue()).replace("C", "");
        	 tempToDb =  Float.valueOf(temp2);
        	 DBConnector.insertQuery("INSERT INTO `tenperatura`(`sortze_ordua`, `tenperatura`) VALUES (CURRENT_TIME," + tempToDb + ");" );
        	 DBConnector.executeQuery("CALL checkAndDelete()");
             break;
        case "Temperature":
        	if (vista.getReceiver() != null) {
            	receivedData = vista.getReceiver().getTemperatureData();
            	tempData = receivedData.replace("t-", "");
            	temp.setTemperatura(Float.valueOf(tempData));
			}
        	break;
        }
		
	}
	
	public ApplicationFrame getVista() {
		return vista;
	}


}

