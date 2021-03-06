package screens.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginController;
import gfx.ResourceHandler;
import screens.frame.LoginFrame;
import screens.templates.CustomPanel;

public class LoginScreen extends CustomPanel implements PropertyChangeListener, FocusListener{

	private static final long serialVersionUID = 1L;

	LoginFrame vista;	
	Font fuenteLogin;
	LoginController controladorLogin;
	JTextField usuarioCampo;
	JPasswordField claveCampo;
	boolean userFieldState, pwdFieldState;
	
	public LoginScreen(LoginFrame vista) {
		
		super(ResourceHandler.getLoginBackground());
		this.vista = vista;
		this.setLayout(null);	
		if (this.vista.getReceiver() != null) {
			this.vista.getReceiver().addPropertyChangeListener(this);
		}		
		userFieldState = false;
		pwdFieldState = false;
		controladorLogin = new LoginController(this.vista, this);

		this.add(crearPanelLogin());		
		try {
			fuenteLogin = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Oliciy.ttf")).deriveFont(12f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(fuenteLogin);
		} catch (FontFormatException | IOException e) {e.printStackTrace(); } 
	}

	private Component crearPanelLogin() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
		panel.setBounds(649, 85, 357, 536);
		panel.add(crearPanelLoginSuperior(), BorderLayout.NORTH);
		panel.add(crearPanelLoginCentral(), BorderLayout.CENTER);
		panel.add(crearPanelLoginInferior(), BorderLayout.SOUTH);
		panel.setOpaque(false);
		return panel;
	}


	private Component crearPanelLoginSuperior() {
		JPanel panel = new JPanel(new BorderLayout());		
		panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 70, 20));

		panel.setOpaque(false);
		return panel;
	}


	private Component crearPanelLoginCentral() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 0, 25));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 70, 20));
		
		JLabel usuarioLabel = new JLabel("Usuario");
		usuarioCampo = new JTextField(10);
		usuarioCampo.addFocusListener(this);
		
		usuarioLabel.setForeground(Color.WHITE);
		usuarioLabel.setFont(new Font("Oliciy", Font.CENTER_BASELINE, 15));
		
		
		panel.add(usuarioLabel);
		panel.add(usuarioCampo);
		
		JLabel claveLabel = new JLabel("Contrase?a");
		claveCampo = new JPasswordField(10);
		claveCampo.addFocusListener(this);
		
		claveLabel.setForeground(Color.WHITE);
		claveLabel.setFont(new Font("Oliciy", Font.CENTER_BASELINE, 15));
		
		panel.add(claveLabel);
		panel.add(claveCampo);
		panel.setOpaque(false);
		
		return panel;
	}
	
	

	private Component crearPanelLoginInferior() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 50, 0));				
		JButton btnConectar = new JButton("Conectar");
		
		panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		btnConectar.setFont(new Font("Oliciy", Font.CENTER_BASELINE, 15));
		btnConectar.setBackground(new Color(149,156,160));
		btnConectar.setForeground(Color.WHITE);
		btnConectar.setActionCommand("btnConectar");
		btnConectar.addActionListener(controladorLogin);
	
				
		panel.add(btnConectar);
		panel.setOpaque(false);
		return panel;
	}

	
	
	
	public JTextField getUsuarioCampo() {
		return usuarioCampo;
	}
	
	public JPasswordField getClaveCampo() {
		return claveCampo;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getComponent() == usuarioCampo) userFieldState = true;
		if(e.getComponent() == claveCampo) pwdFieldState = true;
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getComponent() == usuarioCampo) userFieldState = false;
		if(e.getComponent() == claveCampo) pwdFieldState = false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		String property = (String) arg0.getPropertyName();
		
		switch (property) {
		case "Keypad":
			
			if (userFieldState) {
				if (vista.getReceiver() != null) {
					usuarioCampo.setText(usuarioCampo.getText() + vista.getReceiver().getKeypadData());
				} else JOptionPane.showMessageDialog(null, "Dispositivo STM-32 no conectado", "Error conexi?n", JOptionPane.ERROR_MESSAGE);				
			}
			if (pwdFieldState) {
				if (vista.getReceiver() != null) {
					claveCampo.setText(new String(claveCampo.getPassword()) + vista.getReceiver().getKeypadData());	
				} else JOptionPane.showMessageDialog(null, "Dispositivo STM-32 no conectado", "Error conexi?n", JOptionPane.ERROR_MESSAGE);				
			}
			break;
		}
		
	}
	

}
