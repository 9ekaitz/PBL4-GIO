package screens.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gfx.ResourceHandler;
import screens.frame.ApplicationFrame;
import screens.templates.CustomPanel;

public class WeightScreen extends CustomPanel implements PropertyChangeListener {

	
	private static final long serialVersionUID = 1L;

	ApplicationFrame applicationFrame;
	Font fuenteLogin;
	JLabel label;
	String we,weight = "0 g";
	
	public WeightScreen(ApplicationFrame apliApplicationFrame) {
		super(ResourceHandler.getWeightBackground().getScaledInstance(apliApplicationFrame.getWidth(), apliApplicationFrame.getHeight(), ABORT));
		this.applicationFrame = apliApplicationFrame;
		
		this.setLayout(new BorderLayout());
		if (this.applicationFrame.getReceiver() != null) {
			this.applicationFrame.getReceiver().addPropertyChangeListener(this);
		}
		
		this.add(crearPanelPeso(), BorderLayout.CENTER);
		try {
			fuenteLogin = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Oliciy.ttf")).deriveFont(12f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(fuenteLogin);
		} catch (FontFormatException | IOException e) {e.printStackTrace(); } 
		
	}


	private Component crearPanelPeso() {
		JPanel panel = new JPanel();
		
		label = new JLabel(weight);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Oliciy", Font.CENTER_BASELINE, 60));
		
		panel.add(label);
		panel.setBorder(BorderFactory.createEmptyBorder(100,100 , 0, 100));
		panel.setOpaque(false);
		return panel;
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		String propiedad = (String) evt.getPropertyName();
		
		switch (propiedad) {
		case "Weight":
			we = applicationFrame.getReceiver().getWeightData();
			weight = we.replace("g-", "");
			label.setText(weight+ " g");
			break;
		}		
	}
	
	
}
