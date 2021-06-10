package screens.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import controller.StockController;
import gfx.ResourceHandler;
import model.stock.Material;
import model.stock.MaterialDAOImpl;
import renders.MaterialRenderer;
import screens.frame.ApplicationFrame;
import screens.templates.CustomJList;
import screens.templates.CustomScrollPanel;

public class StockView extends JPanel {

	private JList<Material> list;
	private JTextField searchBar;
	private MaterialDAOImpl model;
	private StockController controller;
	private ApplicationFrame frame;

	public StockView(ApplicationFrame frame) {
		this.setLayout(new BorderLayout());
		model = new MaterialDAOImpl();
		controller = new StockController(this, model, frame);
		createTopBar();
		createMaterialView();
	}

	private void createMaterialView() {
		JScrollPane panel = new CustomScrollPanel(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		list = new JList<>();
		list.setModel(model);
		list.setCellRenderer(new MaterialRenderer());
		list.setOpaque(false);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(controller);
		panel.setViewportView(list);
		this.add(panel, BorderLayout.CENTER);
	}

	private void createTopBar() {
		JPanel bar = new JPanel();
		BoxLayout b = new BoxLayout(bar, BoxLayout.X_AXIS);
		bar.setLayout(b);

		JButton addMaterial = new JButton("Añadir material");
		addMaterial.setIcon(new ImageIcon(ResourceHandler.ADD_PATH_STRING));
		addMaterial.setContentAreaFilled(false);
		addMaterial.setFocusable(false);
		addMaterial.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(5, 5, 5, 5))));
		bar.add(addMaterial);
		bar.add(Box.createHorizontalStrut(800));
		searchBar = new JTextField();
		searchBar.setMaximumSize(new Dimension(800,40));
		searchBar.addKeyListener(controller);
		bar.add(searchBar);
		this.add(bar, BorderLayout.NORTH);
	}
	
	public void setModel(AbstractListModel<Material> model) {
		list.setModel(model);
	}
	
	public ListModel<Material> getModel() {
		return list.getModel();
	}
	
	public String getSearch() {
		return searchBar.getText();
	}
	
}