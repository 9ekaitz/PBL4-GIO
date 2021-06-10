package renders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import gio.gfx.ResourceHandler;
import model.Personal;
import screens.CustomPanel;

public class RendererPersonal implements ListCellRenderer<Personal>{
	

	@Override
	public Component getListCellRendererComponent(JList<? extends Personal> list, Personal value, int index,
			boolean isSelected, boolean cellHasFocus) {
		

		JPanel p=new CustomPanel(ResourceHandler.getBackground(),new GridLayout());
		p.add(crearUno(value));
		p.setBorder(BorderFactory.createEmptyBorder(10, 350, 10, 350));
		p.setVisible(true);
		
		return p;
	}

	private Component crearUno(Personal value) {
		JPanel panel=new JPanel(new BorderLayout(0, 20));
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panel.setSize(1280, 150);
		panel.add(crearPanelTexto(value),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.EAST);
		
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel=new JPanel(new BorderLayout(0,20));
		panel.setBackground(Color.white);
		panel.setSize(1280, 150);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		JButton bEditar=new JButton("Editar");
		bEditar.setActionCommand("editarUsuario");
		bEditar.setBackground(Color.blue);
		bEditar.setForeground(Color.white);
		bEditar.setPreferredSize(new Dimension(150, 50));
		JButton bBorrar=new JButton("Borrar");
		bBorrar.setActionCommand("borrarUsuario");
		bBorrar.setPreferredSize(new Dimension(150, 50));
		bBorrar.setBackground(Color.blue);
		bBorrar.setForeground(Color.white);
		panel.add(bEditar,BorderLayout.SOUTH);
		panel.add(bBorrar,BorderLayout.NORTH);
		
		return panel;
	}

	private Component crearPanelTexto(Personal trabajador) {
		JPanel p=new JPanel(new GridLayout(5,1,10,40));
		p.setBackground(Color.white);
		p.setSize(1280, 150);
		p.setBorder(BorderFactory.createEmptyBorder(40, 80, 2, 0));
		JLabel nombre= new JLabel("Nombre: "+trabajador.getNombre());
		JLabel apellidos= new JLabel("Apellidos: " +trabajador.getApellidos());
		JLabel dni= new JLabel("DNI: "+trabajador.getDNI());
		JLabel puesto= new JLabel("Puesto: "+trabajador.getPuesto());
		
		nombre.setForeground(Color.black);
		nombre.setFont(new Font("arial",Font.ITALIC,18));
		apellidos.setFont(new Font("arial",Font.ITALIC,18));
		dni.setFont(new Font("arial",Font.ITALIC,18));
		puesto.setFont(new Font("arial",Font.ITALIC,18));
		
		p.add(nombre);
		p.add(apellidos);
		p.add(dni);
		p.add(puesto);
		
		return p;
	}

}
