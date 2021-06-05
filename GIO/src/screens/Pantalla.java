package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.PersonalDAOImpl;
import model.Trabajador;
import renders.RendererPersonal;


public class Pantalla extends JPanel implements ActionListener,PropertyChangeListener{
	
	PersonalDAOImpl listaTrabajadores;
	JList<Trabajador>jlistTrabajadores;
	RendererPersonal renderer;

	public Pantalla() {
		this.setLayout(new BorderLayout(0, 20));
		this.listaTrabajadores = new PersonalDAOImpl();
		this.add(crearPanelBoton(),BorderLayout.NORTH);
		this.add(crearPanelLista(),BorderLayout.CENTER);
	}

	private Component crearPanelLista() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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

		JButton aņadirTrabajador = new JButton("Aņadir trabajador");
		aņadirTrabajador.setContentAreaFilled(false);
		aņadirTrabajador.setFocusable(false);
		aņadirTrabajador.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(5, 5, 5, 5))));
		
		aņadirTrabajador.setActionCommand("aņadir");
		aņadirTrabajador.addActionListener(this);
		bar.add(aņadirTrabajador);
		
		return bar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "aņadir":
			DialogoNuevoUsuario dialogo=new DialogoNuevoUsuario(Pantalla.this, "Nuevo Usuario", true);
			Trabajador trabajador=dialogo.getTrabajador();
			if(trabajador!=null)listaTrabajadores.add(trabajador);
			break;
	}		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.repaint();
		
	}
	
	

}
