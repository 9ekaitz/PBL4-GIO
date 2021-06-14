package screens.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gfx.ResourceHandler;
import model.Codificar;
import model.staff.Personal;
import screens.templates.CustomPanel;

public class DialogoNuevoUsuario extends JDialog implements ActionListener{
	
	JTextField nombre,apellido,dni,puesto,usuario,passwd;
	Personal newTrabajador = null;
	PropertyChangeListener lst;
	private String passwdHash;
	Codificar codificar;
	
	public DialogoNuevoUsuario(JPanel ventana, String titulo, boolean modo){
		super();
		this.setTitle(titulo);
		this.setModal(modo);
		this.setSize(800,500);
		this.setLocation(200,200);
		codificar = new Codificar();
		this.lst=(PropertyChangeListener)ventana;
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new CustomPanel(ResourceHandler.getBackground(),new BorderLayout(0,10));
		panel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		panel.add(crearPanelDatos(),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelDatos() {
		JPanel panel = new CustomPanel (ResourceHandler.getBackground(),new GridLayout(6,1,0,5));
		nombre = new JTextField(80);
		apellido = new JTextField(80);
		dni = new JTextField (80);
		puesto = new JTextField (80);
		usuario = new JTextField (80);
		passwd = new JTextField (80);
		panel.add(crearTextField(nombre,"Nombre:         "));
		panel.add(crearTextField(apellido,"Apellidos:       "));
		panel.add(crearTextField(dni,"DNI:                  "));
		panel.add(crearTextField(puesto,"Puesto:           "));
		panel.add(crearTextField(usuario,"Usuario:          "));
		panel.add(crearTextField(passwd,"Contraseï¿œa:  "));


		return panel;
	}

	private Component crearTextField(JTextField text, String titulo) {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setBackground(Color.white);
		text.setPreferredSize(new Dimension(100,50));
		JLabel n=new JLabel(titulo);
		panel.add(n,BorderLayout.WEST);
		panel.add(text,BorderLayout.CENTER);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new CustomPanel (ResourceHandler.getBackground());
		JButton bCrear = new JButton ("Crear");
		bCrear.setActionCommand("crear");
		bCrear.addActionListener(this);
		bCrear.setPreferredSize(new Dimension(100,60));
		bCrear.setContentAreaFilled(false);
		bCrear.setBackground(Color.white);
		bCrear.setOpaque(true);
		
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("cancel");
		bCancel.addActionListener(this);
		bCancel.setPreferredSize(new Dimension(100,60));
		bCancel.setContentAreaFilled(false);
		bCancel.setBackground(Color.white);
		bCancel.setOpaque(true);
		
		panel.add(bCrear);
		panel.add(bCancel);
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("crear")){
			if (checkDataValid(nombre, apellido, dni, puesto, usuario, passwd)) {
				passwdHash = codificar.codificar(passwd.getText());				
				newTrabajador = new Personal(nombre.getText(), apellido.getText(), dni.getText(), usuario.getText(), passwdHash, puesto.getText());
				dispose();
			}		
		}
		if (e.getActionCommand().equals("cancel")){
			newTrabajador=null;
			dispose();
		}
	}
	public Personal getTrabajador(){
		return newTrabajador;
	}
	
	
	public boolean checkDataValid(JTextField nombre,JTextField apellido,JTextField dni,JTextField puesto,JTextField usuario,JTextField passwd)
	{
		boolean checkIsNotEmpty = false;
		boolean valid = false;
		
		if (!nombre.getText().isEmpty() && !apellido.getText().isEmpty() && !dni.getText().isEmpty() && !puesto.getText().isEmpty() && !usuario.getText().isEmpty() && !passwd.getText().isEmpty()) 
		{
			checkIsNotEmpty = true; 
		} else JOptionPane.showMessageDialog(null, "Datos incompletos", "Error Registro", JOptionPane.ERROR_MESSAGE);
		if (checkIsNotEmpty) {
			if (isNumeric(usuario.getText()) && isNumeric(passwd.getText())) {
				valid = true;
			} else JOptionPane.showMessageDialog(null, "Datos de usuario o contraseña en formato incorrecto", "Error registro", JOptionPane.ERROR_MESSAGE);
		}
		
		return valid;
	}
	
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}