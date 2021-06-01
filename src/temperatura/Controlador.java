package temperatura;

import vista.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    Vista vista;

    public Controlador(Vista vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String comando = e.getActionCommand();

       switch (comando){
           case "act":
                //activar ventilador

               break;
           case "des":
                //desactivar ventilador

               break;
           case "not":
                //notificar problema

               break;
       }
    }
}
