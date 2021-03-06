package screens.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.MenuController;
import gfx.ResourceHandler;
import screens.frame.ApplicationFrame;
import screens.templates.CustomPanel;

public class MainMenu extends CustomPanel {

	private final static int LEFT_MARGIN = 10;
	private final static int RIGHT_MARGIN = 10;
	private final static int TOP_MARGIN = 30;
	private final static int BOTTOM_MARGIN = 30;
	private final static int INNER_MARGIN = 10;

	private ApplicationFrame frame;
	private MenuController controller;


	public MainMenu(ApplicationFrame frame) {
		super(ResourceHandler.getBackground());

		this.frame = frame;
		this.controller = new MenuController(frame);
		this.setLayout(new GridBagLayout());
		createPediosButton();
		createStockButton();
		createTempButton();
		createUsersButton();
		createStatsButton();
	}

	private void createPediosButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(TOP_MARGIN, LEFT_MARGIN, INNER_MARGIN, 52);
		constraints.weightx = 0.66;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.SOUTHEAST;

		JButton pedidos = new JButton();
		pedidos.setPreferredSize(new Dimension(1162, 400));
		pedidos.setActionCommand("screen-pedidos");
		pedidos.addActionListener(controller);
		pedidos.setIcon(new ImageIcon(getClass().getResource(ResourceHandler.PEDIDOS_PATH)));
		pedidos.setRolloverIcon(new ImageIcon(getClass().getResource(ResourceHandler.PEDIDOS_ROLL_PATH)));
		pedidos.setBorder(null);
		pedidos.addActionListener(controller);
		this.add(pedidos, constraints);
	}

	private void createStockButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(TOP_MARGIN, INNER_MARGIN, INNER_MARGIN, RIGHT_MARGIN);
		constraints.weightx = 0.33;
		constraints.weighty = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.SOUTHWEST;
		JButton stock = new JButton();
		stock.setPreferredSize(new Dimension(550, 400));
		stock.setIcon(new ImageIcon(getClass().getResource(ResourceHandler.STOCK_PATH)));
		stock.setRolloverIcon(new ImageIcon(getClass().getResource(ResourceHandler.STOCK_ROLL_PATH)));
		stock.setBorder(null);
		stock.setActionCommand("screen-stock");
		stock.addActionListener(controller);
		this.add(stock, constraints);
	}

	private void createTempButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(INNER_MARGIN, LEFT_MARGIN, BOTTOM_MARGIN, INNER_MARGIN);
		constraints.weightx = 0.33;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		JButton temp = new JButton();
		temp.setActionCommand("screen-temp");
		temp.addActionListener(controller);
		temp.setPreferredSize(new Dimension(550, 400));
		temp.setIcon(new ImageIcon(getClass().getResource(ResourceHandler.TEMP_PATH)));
		temp.setRolloverIcon(new ImageIcon(getClass().getResource(ResourceHandler.TEMP_ROLL_PATH)));
		temp.setBorder(null);
		this.add(temp, constraints);
	}

	private void createStatsButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(INNER_MARGIN, INNER_MARGIN, BOTTOM_MARGIN, INNER_MARGIN);
		constraints.weightx = 0.33;
		constraints.weighty = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTH;
		JButton stats = new JButton();
		stats.setPreferredSize(new Dimension(550, 400));
		stats.setIcon(new ImageIcon(getClass().getResource(ResourceHandler.BA_PATH)));
		stats.setRolloverIcon(new ImageIcon(getClass().getResource(ResourceHandler.BA_ROLL_PATH)));
		stats.setBorder(null);
		stats.setActionCommand("screen-weight");
		stats.addActionListener(controller);
		this.add(stats, constraints);
	}

	private void createUsersButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(INNER_MARGIN, INNER_MARGIN, BOTTOM_MARGIN, RIGHT_MARGIN);
		constraints.weightx = 0.33;
		constraints.weighty = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		JButton users = new JButton();
		users.setActionCommand("screen-users");
		users.addActionListener(controller);
		users.setPreferredSize(new Dimension(550, 400));
		users.setIcon(new ImageIcon(getClass().getResource(ResourceHandler.USERS_PATH)));
		users.setRolloverIcon(new ImageIcon(getClass().getResource(ResourceHandler.USERS_ROLL_PATH)));
		users.setBorder(null);
		this.add(users, constraints);
	}

}
