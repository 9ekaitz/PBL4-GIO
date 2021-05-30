package tmp;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.MenuController;
import frame.ApplicationFrame;

public class MainMenu extends JPanel{

	private final static String BACKGORUND_PATH = "res/panel-fondo.png";
	private final static String PEDIDOS_PATH = "res/pedidos1.png";
	private final static String PEDIDOS_ROLL_PATH = "res/pedidos-roll.png";
	private final static String STOCK_PATH = "res/stock.png";
	private final static String TEMP_PATH = "res/temperatura.png";
	
	ApplicationFrame frame;
	Image background;
	MenuController controller;
	
	public MainMenu(ApplicationFrame frame) {
		this.controller = new MenuController(frame);
		this.setLayout(new GridBagLayout());
		loadBackground();
		createPediosButton();
		createStockButton();
		createTempButton();
		createUsersButton();
		createStatsButton();
	}
	
	

	private void loadBackground() {
		try {
			background = ImageIO.read(new File(BACKGORUND_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}



	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}



	private void createPediosButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(30, 5, 10, 45);
		constraints.weightx = 0.66;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.SOUTHEAST;
		
		JButton pedidos = new JButton();
		pedidos.setPreferredSize(new Dimension(725, 260));
		pedidos.setIcon(new ImageIcon(PEDIDOS_PATH));
		pedidos.setRolloverIcon(new ImageIcon(PEDIDOS_ROLL_PATH));
		pedidos.setBorder(null);
		pedidos.setActionCommand("screen-stock");
		pedidos.addActionListener(controller);
		this.add(pedidos, constraints);
	}
	

	private void createStockButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(30, 30, 10, 20);
		constraints.weightx = 0.33;
		constraints.weighty = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.SOUTHWEST;
		JButton stock = new JButton();
		stock.setPreferredSize(new Dimension(325, 260));
		stock.setIcon(new ImageIcon(STOCK_PATH));
		this.add(stock, constraints);
	}
	
	private void createTempButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 30, 5);
		constraints.weightx = 0.33;
		constraints.weighty = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		JButton temp = new JButton();
		temp.setPreferredSize(new Dimension(325, 260));
		temp.setIcon(new ImageIcon(TEMP_PATH));
		this.add(temp, constraints);
	}
	
	private void createStatsButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 30, 30, 5);
		constraints.weightx = 0.33;
		constraints.weighty = 0.5;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTH;
		JButton stats = new JButton();
		stats.setPreferredSize(new Dimension(325, 260));
		this.add(stats, constraints);
	}
	
	private void createUsersButton() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 30, 30, 10);
		constraints.weightx = 0.33;
		constraints.weighty = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		JButton users = new JButton();
		users.setPreferredSize(new Dimension(325, 260));
		this.add(users, constraints);
	}

}
