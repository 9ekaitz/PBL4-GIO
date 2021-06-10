package screens.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleRole;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.StockController;
import gfx.ResourceHandler;
import model.box.BoxVO;
import model.box.BoxDAOImpl;
import model.stock.Material;
import renders.BoxLstRenderer;
import screens.templates.CustomJList;
import screens.templates.CustomPanel;
import screens.templates.CustomScrollPanel;

public class DetailsPanel extends CustomPanel{

	private BoxDAOImpl model;
	private Material material;
	private JList<BoxVO> lst;
	private StockController controller;
	private JCheckBox fiveK, tenK, fiveteenK;
	
	public DetailsPanel(Material m, StockController controller) {
		super(ResourceHandler.getBackground(), new BorderLayout());
		this.controller = controller;
		this.material = m;
		controller.setBoxView(this);
		model = new BoxDAOImpl(m);
		
		createTopBar();
		createBoxView();
	}

	private void createTopBar() {
		JPanel bar = new JPanel();
		BoxLayout b = new BoxLayout(bar, BoxLayout.X_AXIS);
		bar.setLayout(b);

		JButton addMaterial = new JButton("Crear caja");
		addMaterial.setIcon(new ImageIcon(ResourceHandler.CREATE_PATH_STRING));
		addMaterial.setContentAreaFilled(false);
		addMaterial.setFocusable(false);
		addMaterial.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,10,5),BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(5, 5, 5, 5))));
		addMaterial.setActionCommand("create-box");
		addMaterial.addActionListener(controller);
		bar.add(addMaterial);
		bar.add(javax.swing.Box.createHorizontalStrut(800));
		this.add(bar, BorderLayout.NORTH);
	}

	private void createBoxView() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel optionPanel = new JPanel(new BorderLayout());
		optionPanel.setPreferredSize(new Dimension(200, 100));
		JLabel materialLabel = new JLabel(material.getName(), JLabel.CENTER);
		materialLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		optionPanel.add(materialLabel, BorderLayout.NORTH);
		
		CustomPanel checkBoxPanel = new CustomPanel(ResourceHandler.getBackground());
		JPanel bgPanel = new JPanel();
		bgPanel.setLayout(new BoxLayout(bgPanel, BoxLayout.Y_AXIS));
		bgPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,5));
		bgPanel.setPreferredSize(new Dimension(200,100));
		fiveK = new JCheckBox("5Kg");
		fiveK.addItemListener(controller);
		tenK = new JCheckBox("10Kg");
		tenK.addItemListener(controller);
		fiveteenK = new JCheckBox("15Kg");
		fiveteenK.addItemListener(controller);
		bgPanel.add(fiveK);
		bgPanel.add(javax.swing.Box.createVerticalStrut(10));
		bgPanel.add(tenK);
		bgPanel.add(javax.swing.Box.createVerticalStrut(10));
		bgPanel.add(fiveteenK);
		checkBoxPanel.add(bgPanel);
		optionPanel.add(checkBoxPanel, BorderLayout.CENTER);
		
		
		JScrollPane scroll = new CustomScrollPanel(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lst = new CustomJList<>();
	
		lst.setModel(model);
		lst.setCellRenderer(new BoxLstRenderer());
		lst.setOpaque(false);
		scroll.setViewportView(lst);
		
		panel.add(optionPanel, BorderLayout.WEST);
		panel.add(scroll, BorderLayout.CENTER);
		this.add(panel, BorderLayout.CENTER);
	}
	
	public boolean is5Checked() {
		return fiveK.isSelected();
	}
	
	public boolean is10Checked() {
		return tenK.isSelected();
	}
	
	public boolean is15Checked() {
		return fiveteenK.isSelected();
	}
	
	public BoxDAOImpl getModel() {
		return model;
	}
	
	public void setModel(BoxDAOImpl model) {
		lst.setModel(model);
	}
}
