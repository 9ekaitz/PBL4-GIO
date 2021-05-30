package vista;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Recurso;

public class PantallaBorrador extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String FONDO = "borrador-background.png";
	
	Vista vista;

	public PantallaBorrador(Vista vista) {
		super();
		this.vista = vista;
		this.setLayout(new BorderLayout());		
		
		
		this.add(crearPanelCentral(), BorderLayout.CENTER);
		
	}

	private Component crearPanelCentral() {
		PanelFondo panelCentral = new PanelFondo(new ImageIcon(Recurso.PATH_FONDOS + FONDO));
		
		
		
		JLabel label = new JLabel(vista.personal.toString());
		
		panelCentral.add(label);
		
		
		return panelCentral;
	}
	
	
	
	
	
	
	
	
	
}
