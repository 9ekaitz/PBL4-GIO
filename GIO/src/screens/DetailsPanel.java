package screens;

import javax.swing.JList;
import javax.swing.JScrollPane;

import gio.gfx.ResourceHandler;
import model.Box;
import model.BoxDAOImpl;
import model.Material;

public class DetailsPanel extends CustomPanel{

	BoxDAOImpl model;
	
	public DetailsPanel(Material m) {
		super(ResourceHandler.getBackground());
		model = new BoxDAOImpl(m);
		createJList();

	}

	private void createJList() {
		JScrollPane panel = new JScrollPane();
		JList<Box> list = new JList<>();
	
		list.setModel(model);
	}
	
	
}
