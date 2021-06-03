package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorLogin;
import gio.gfx.ResourceHandler;
import modelo.Recurso;
import screens.CustomPanel;

public class PantallaLogin extends CustomPanel{

	private static final long serialVersionUID = 1L;

	Vista vista;	
	Font fuenteLogin;
	ControladorLogin controladorLogin;
	JTextField usuarioCampo;
	JPasswordField claveCampo;
	
	public PantallaLogin(Vista vista) {
		
		super(ResourceHandler.getLoginBackground());
		this.vista = vista;
		this.setLayout(null);		
		
		controladorLogin = new ControladorLogin(this.vista, this);

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
		
		usuarioLabel.setForeground(Color.WHITE);
		usuarioLabel.setFont(new Font("Oliciy", Font.CENTER_BASELINE, 15));
		
		
		panel.add(usuarioLabel);
		panel.add(usuarioCampo);
		
		JLabel claveLabel = new JLabel("Contrase�a");
		claveCampo = new JPasswordField(10);
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
	

}
