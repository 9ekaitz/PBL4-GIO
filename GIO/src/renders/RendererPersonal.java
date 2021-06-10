package renders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import model.Trabajador;

public class RendererPersonal implements ListCellRenderer<Trabajador>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Trabajador> list, Trabajador value, int index,
			boolean isSelected, boolean cellHasFocus) {

		JPanel p=new JPanel(new GridLayout());
		p.setBackground(Color.white);
		p.add(crearUno(value));
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p.setVisible(true);
		
		return p;
	}

	private Component crearUno(Trabajador value) {
		JPanel panel=new JPanel(new BorderLayout(0, 20));
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panel.add(crearPanelTexto(value),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.EAST);
		
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel=new JPanel(new BorderLayout(0,20));
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		JButton bEditar=new JButton("Editar");
		bEditar.setActionCommand("editarUsuario");
		JButton bBorrar=new JButton("Borrar");
		bBorrar.setActionCommand("borrarUsuario");
		panel.add(bEditar,BorderLayout.SOUTH);
		panel.add(bBorrar,BorderLayout.NORTH);
		
		return panel;
	}

	private Component crearPanelTexto(Trabajador trabajador) {
		JPanel p=new JPanel(new GridLayout(4,1,0,10));
		p.setBackground(Color.white);
		p.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 0));
		JLabel nombre= new JLabel("Nombre: "+trabajador.getNombre());
		JLabel apellidos= new JLabel("Apellidos: " +trabajador.getApellidos());
		JLabel dni= new JLabel("DNI: "+trabajador.getDNI());
		JLabel puesto= new JLabel("Puesto: "+trabajador.getPuesto());
		
		nombre.setForeground(Color.black);
		nombre.setFont(new Font("arial",Font.ITALIC,14));
		apellidos.setFont(new Font("arial",Font.ITALIC,14));
		dni.setFont(new Font("arial",Font.ITALIC,14));
		puesto.setFont(new Font("arial",Font.ITALIC,14));;
		
		p.add(nombre);
		p.add(apellidos);
		p.add(dni);
		p.add(puesto);
		
		return p;
	}

}
