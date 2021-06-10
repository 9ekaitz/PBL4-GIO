package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ListaTrabajadores;
import model.Trabajador;
import renders.RendererPersonal;


public class StaffScreen extends JPanel{
	

	private static final long serialVersionUID = 1L;
	ListaTrabajadores listaTrabajadores;
	JList<Trabajador>jlistTrabajadores;
	RendererPersonal renderer;

	public StaffScreen() {
		this.setLayout(new BorderLayout(0, 20));
		this.listaTrabajadores = new ListaTrabajadores();
		this.add(crearPanelBoton(),BorderLayout.NORTH);
		this.add(crearPanelLista(),BorderLayout.CENTER);
	}

	private Component crearPanelLista() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		jlistTrabajadores=new JList<>();
		jlistTrabajadores.setListData(listaTrabajadores.getTrabajadores());
		renderer=new RendererPersonal();
		jlistTrabajadores.setCellRenderer(renderer);
		panel.setViewportView(jlistTrabajadores);
		return panel;
	}

	private Component crearPanelBoton() {
		JPanel bar = new JPanel();
		BoxLayout b = new BoxLayout(bar, BoxLayout.X_AXIS);
		bar.setLayout(b);

		JButton addTrabajador = new JButton("A�adir trabajador");
		addTrabajador.setContentAreaFilled(false);
		addTrabajador.setFocusable(false);
		addTrabajador.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(5, 5, 5, 5))));
		bar.add(addTrabajador);
		
		return bar;
	}
	
	

}
