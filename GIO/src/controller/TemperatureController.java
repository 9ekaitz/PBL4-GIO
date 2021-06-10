package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import screens.panel.TemperatureScreen;

public class TemperatureController implements ActionListener {

    TemperatureScreen vista;
    boolean estado = false;

    public TemperatureController(TemperatureScreen vistaTemperatura){
        this.vista = vistaTemperatura;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String comando = e.getActionCommand();

       switch (comando){
           case "act":
        	   if (!estado) {
        		   if (vista.getVista().getTransmiter() != null) {
            		   vista.getVista().getTransmiter().send("e");
            		   estado = true;
				}

        	   }               
               break;
           case "des":
        	   if(estado)
        	   {
        		   if (vista.getVista().getTransmiter() != null) {
            		   vista.getVista().getTransmiter().send("e");
            		   estado = false;
				}

        	   }
               break;
           case "not":
                //notificar problema

               break;
       }
    }
}
