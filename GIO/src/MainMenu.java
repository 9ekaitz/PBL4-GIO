import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel{

	public MainMenu() {
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		createPediosButton();
		createStockButton();
		createTempButton();
		createUsersButton();
		createStatsButton();
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
