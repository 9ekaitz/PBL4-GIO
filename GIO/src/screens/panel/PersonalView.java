package screens.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.staff.Personal;
import model.staff.PersonalDAOImpl;
import renders.RendererPersonal;
import screens.dialog.DialogoNuevoUsuario;


public class PersonalView extends JPanel implements ActionListener,PropertyChangeListener{
	
	PersonalDAOImpl listaTrabajadores;
	JList<Personal>jlistTrabajadores;
	RendererPersonal renderer;

	public PersonalView() {
		this.setLayout(new BorderLayout(0, 20));
		this.listaTrabajadores = new PersonalDAOImpl();
		this.add(crearPanelBoton(),BorderLayout.NORTH);
		this.add(crearPanelLista(),BorderLayout.CENTER);
	}

	private Component crearPanelLista() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jlistTrabajadores=new JList<>();
		jlistTrabajadores.setModel(listaTrabajadores);
		renderer=new RendererPersonal();
		jlistTrabajadores.setCellRenderer(renderer);
		panel.setViewportView(jlistTrabajadores);
		return panel;
	}

	private Component crearPanelBoton() {
		JPanel bar = new JPanel();
		BoxLayout b = new BoxLayout(bar, BoxLayout.X_AXIS);
		bar.setLayout(b);

		JButton addTrabajador = new JButton("A�adir usuario");
		addTrabajador.setContentAreaFilled(false);
		addTrabajador.setFocusable(false);
		addTrabajador.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(5, 5, 5, 5))));
		
		addTrabajador.setActionCommand("add");
		addTrabajador.addActionListener(this);
		bar.add(addTrabajador);
		
		return bar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "add":
			DialogoNuevoUsuario dialogo=new DialogoNuevoUsuario(PersonalView.this, "Nuevo Usuario", true);
			Personal trabajador=dialogo.getTrabajador();
			if(trabajador!=null)listaTrabajadores.addTrabajador(trabajador);
			break;
	}		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.repaint();
		
	}
	
	

}
